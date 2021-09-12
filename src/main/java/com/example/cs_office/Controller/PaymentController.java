package com.example.cs_office.Controller;

import javax.servlet.http.HttpServletRequest;

import com.example.cs_office.Model.Dto.Pay;
import com.example.cs_office.Service.BookService;
import com.example.cs_office.Service.PaypalService;
import com.example.cs_office.Util.Utils;
import com.example.cs_office.config.PaypalPaymentIntent;
import com.example.cs_office.config.PaypalPaymentMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller()
@CrossOrigin("*")
public class PaymentController {

    public static final String URL_PAYPAL_SUCCESS = "pay/success";
    public static final String URL_PAYPAL_CANCEL = "pay/cancel";
    public static int idOrders = 0;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PaypalService paypalService;

    @Autowired
    private BookService bookService;

    @GetMapping("/pay")
    public String pay(HttpServletRequest request,@Param("total") double total, @Param("idOrderDetail") int idOrderDetail ){
        String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
        String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
        idOrders = idOrderDetail;
        try {
            Payment payment = paypalService.createPayment(
                    total,
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    cancelUrl,
                    successUrl);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping(URL_PAYPAL_CANCEL)
    public String cancelPay(){
        return "cancel";
    }

    @GetMapping(URL_PAYPAL_SUCCESS)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
        try {
            if(idOrders != 0) {
                System.out.println("idOrders " + idOrders);
                Payment payment = paypalService.executePayment(paymentId, payerId);
                if(payment.getState().equals("approved")){
                    bookService.paySuccess(idOrders);
                    return "success";
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }

}
