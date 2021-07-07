package com.example.cs_office.Controller;

import com.example.cs_office.Config.JwtTokenUtil;
import com.example.cs_office.Model.Dto.CustomerDto;
import com.example.cs_office.Model.Dto.StaffDto;
import com.example.cs_office.Model.Jwt.JwtRequest;
import com.example.cs_office.Model.Jwt.JwtResponse;
import com.example.cs_office.Service.JwtCustomerDetailsService;
import com.example.cs_office.Service.JwtStaffDetailsService;
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
@CrossOrigin
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtCustomerDetailsService userDetailsService;

    @Autowired
    private JwtStaffDetailsService jwtStaffDetailsService;

    @RequestMapping(value = PathResources.CUSTOMERLOGIN, method = RequestMethod.POST)
    public ResponseEntity<?> customerLoginToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token, authenticationRequest.getEmail()));
    }

    @RequestMapping(value = PathResources.STAFFLOGIN, method = RequestMethod.POST)
    public ResponseEntity<?> staffLoginToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

//        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtStaffDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token, authenticationRequest.getEmail()));
    }

    @RequestMapping(value = PathResources.REGISTER, method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody CustomerDto customerDto) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(customerDto));
    }

    @RequestMapping(value = PathResources.INSERTSTAFF, method = RequestMethod.POST)
    public ResponseEntity<?> saveStaff(@RequestBody StaffDto staffDto) throws Exception {
        return ResponseEntity.ok(jwtStaffDetailsService.save(staffDto));
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
