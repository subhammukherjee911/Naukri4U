package com.example.NAOSys.Controller;

import com.example.NAOSys.Entity.*;
import com.example.NAOSys.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Naukri4U")
public class UserController
{
    @Autowired
    UserService userService;

    @PostMapping("/addRecruiter")
    public ResponseEntity<String> registerRecruiter(@RequestBody RecruiterRegistration recruiter)
    {
        String validation = userService.addRecruiter(recruiter.getUser(), recruiter.getRecruiter());
        if(validation != null)
        {
            return new ResponseEntity<>("Recruiter has been added successfully", HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addCandidate")
    public ResponseEntity<String> registerCandidate(@RequestBody CandidateRegistration candidate)
    {
        String validation = userService.addCandidate(candidate.getUser(), candidate.getCandidate());
        if(validation != null)
        {
            return new ResponseEntity<>("Candidate has been added successfully", HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllRecruiter")
    public ResponseEntity<String> viewAllRecruiter()
    {
        String validation = userService.getAllRecruiter();
        if(validation != null)
        {
            return new ResponseEntity<>(userService.getAllRecruiter(), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getRecruiterEmail/{email}")
    public ResponseEntity<String> viewRecruiterByEmail(@PathVariable("email") String email)
    {
        String validation = userService.getRecruiterByEmail(email);
        if(validation!=null)
        {
            return new ResponseEntity<>(userService.getRecruiterByEmail(email), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getRecruiterPhone/{phone}")
    public ResponseEntity<String> viewRecruiterByPhone(@PathVariable("phone") String phone)
    {
        String validation = userService.getRecruiterByPhone(phone);
        if(validation!=null)
        {
            return new ResponseEntity<>(userService.getRecruiterByPhone(phone), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllCandidate")
    public ResponseEntity<String> viewAllCandidate()
    {
        String validation = userService.getAllCandidate();
        if(validation != null)
        {
            return new ResponseEntity<>(userService.getAllCandidate(), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
