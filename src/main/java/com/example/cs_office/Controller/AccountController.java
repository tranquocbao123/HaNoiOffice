package com.example.cs_office.Controller;

import com.example.cs_office.Config.JwtTokenUtil;
import com.example.cs_office.Model.Dto.UserChangePassword;
import com.example.cs_office.Model.Dto.UserLogin;
import com.example.cs_office.Model.Entity.Customer;
import com.example.cs_office.Model.Jwt.JwtRequest;
import com.example.cs_office.Model.Jwt.JwtResponse;
import com.example.cs_office.Service.CustomerService;
import com.example.cs_office.Service.Jwt.JwtUserDetailsService;
import com.example.cs_office.Util.PathResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping(PathResources.ACCOUNT)
@CrossOrigin("*")
public class AccountController {

    private CustomerService customerService;

    @Autowired
    public AccountController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        System.out.println("Step  ------------> 0");
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        System.out.println("Step  ------------> 1");
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        System.out.println("Step ------------> 2");
        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println("Step ------------------> 3");
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody Customer customer) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(customer));
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    //login customer
    @PostMapping(PathResources.LOGIN)
    public void login(@RequestBody UserLogin userLogin) {
        customerService.login(userLogin);
    }

    //forgot password customer
    @RequestMapping(PathResources.FORGOTPASSWORD)
    public Customer forgotPassword(@PathVariable String email){
        return customerService.findByEmail(email);
    }

    //change password customer
    @PutMapping(PathResources.CHANGEPASSWORD)
    public void changePassword (@RequestBody UserChangePassword user) {
        customerService.changePassword(user);
    }

    //rigistation customer
    @PostMapping(PathResources.SAVE)
    public void registationCustomer(@RequestBody Customer customer){
        customerService.addNewCustomer(customer);
    }

}
