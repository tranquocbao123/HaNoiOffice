package com.example.cs_office.Controller;

import com.example.cs_office.Model.Dto.*;
import com.example.cs_office.Model.Jwt.JwtRequest;
import com.example.cs_office.Model.Jwt.JwtResponse;
import com.example.cs_office.Service.JwtUserDetailsService;
import com.example.cs_office.Util.PathResources;
import com.example.cs_office.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @RequestMapping(value = PathResources.LOGIN, method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token, authenticationRequest.getEmail()));
    }

    @RequestMapping(value = PathResources.CUSTOMERCHANGEPASSWORD, method = RequestMethod.PUT)
    public ResponseEntity<?> customerChangePassword(@RequestBody UserChangePassword userChangePassword, @PathVariable("customerId") int customerId) throws Exception {

        authenticate(userChangePassword.getEmail(), userChangePassword.getPasswordOld());

        return ResponseEntity.ok(jwtUserDetailsService.cutomerChangePassword(customerId, userChangePassword.getPasswordOld(), userChangePassword.getPasswordNew()));
    }

    @RequestMapping(value = PathResources.STAFFCHANGEPASSWORD, method = RequestMethod.PUT)
    public ResponseEntity<?> staffChangePassword(@RequestBody UserChangePassword userChangePassword, @PathVariable("staffId") int customerId) throws Exception {

        authenticate(userChangePassword.getEmail(), userChangePassword.getPasswordOld());

        return ResponseEntity.ok(jwtUserDetailsService.staffChangePassword(customerId, userChangePassword.getPasswordOld(), userChangePassword.getPasswordNew()));
    }

    @RequestMapping(value = PathResources.REGISTER, method = RequestMethod.POST)
    public ResponseEntity<?> saveCustomer(@RequestBody CustomerDto customerDto) throws Exception {
        return ResponseEntity.ok(jwtUserDetailsService.saveCustomer(customerDto));
    }

    @RequestMapping(value = PathResources.INSERTSTAFF, method = RequestMethod.POST)
    public ResponseEntity<?> saveStaff(@RequestBody Staff staffDto) throws Exception {
        return ResponseEntity.ok(jwtUserDetailsService.saveStaff(staffDto));
    }

//    @RequestMapping(value = PathResources.LOGOUT, method = RequestMethod.POST)
//    public ResponseEntity<?> logout() throws Exception {
//        jwtTokenUtil.generateToken(null);
//        System.out.println("jwtTokenUtil " + jwtTokenUtil );
//        return ResponseEntity.ok(jwtUserDetailsService.logout());
//    }

    @RequestMapping(value = PathResources.FORGOTPASSWORD, method = RequestMethod.POST)
    public ResponseEntity<?> forgotPassword(@RequestBody EmailDto email) throws MessagingException {
        return ResponseEntity.ok(jwtUserDetailsService.forgotPassword(email));
    }

    @RequestMapping(value = PathResources.RESETPASSWORD, method = RequestMethod.POST)
    public void resetPassword(@RequestBody UserResetPassword user){
        jwtUserDetailsService.resetPassword(user);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
