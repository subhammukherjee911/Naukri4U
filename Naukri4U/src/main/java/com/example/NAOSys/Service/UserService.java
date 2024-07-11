package com.example.NAOSys.Service;

import com.example.NAOSys.CustomMethods.Validator;
import com.example.NAOSys.Entity.Candidate;
import com.example.NAOSys.Entity.Recruiter;
import com.example.NAOSys.Entity.User;
import com.example.NAOSys.Repository.CandidateRepo;
import com.example.NAOSys.Repository.RecruiterRepo;
import com.example.NAOSys.Repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService
{
    @Autowired
    UserRepo userRepo;

    @Autowired
    RecruiterRepo recruiterRepo;

    @Autowired
    CandidateRepo candidateRepo;

    public String addRecruiter(User user, Recruiter recruiter)
    {
        Optional<User> validateEmailExists = userRepo.findByEmail(user.getEmail());
        Optional<User> validatePhoneExists = userRepo.findByPhone(user.getPhone());
        if(validateEmailExists.isPresent())
        {
            return "Recruiter profile already exists with the provided email address "+user.getEmail();
        }
        else if(validatePhoneExists.isPresent())
        {
            return "Recruiter profile already exists with the provided phone number "+user.getPhone();
        }
        else
        {
            if(Validator.validateEmail(user.getEmail(), "Recruiter"))
            {
                if(Validator.validatePhone(user.getPhone()))
                {
                    if(Validator.validatePinCode(user.getPincode()))
                    {
                        user.setRole("Recruiter");
                        user.setRecruiter(recruiter);
                        recruiter.setUser(user);
                        userRepo.save(user);
                        recruiterRepo.save(recruiter);
                        return "Recruiter has been added successfully";
                    }
                    else
                    {
                        return "Invalid pincode!";
                    }
                }
                else
                {
                    return "Invalid phone number";
                }
            }
            else
            {
                return "Invalid email address";
            }
        }
    }

    public String addCandidate(User user, Candidate candidate)
    {
        Optional<User> validateEmailExists = userRepo.findByEmail(user.getEmail());
        Optional<User> validatePhoneExists = userRepo.findByPhone(user.getPhone());
        if(validateEmailExists.isPresent())
        {
            return "Candidate profile already exists with the provided email address "+user.getEmail();
        }
        else if(validatePhoneExists.isPresent())
        {
            return "Candidate profile already exists with the provided phone number "+user.getPhone();
        }
        else
        {
            if(Validator.validateEmail(user.getEmail(), "Candidate"))
            {
                if(Validator.validatePhone(user.getPhone()))
                {
                    if(Validator.validatePinCode(user.getPincode()))
                    {
                        user.setRole("Candidate");
                        user.setCandidate(candidate);
                        candidate.setUser(user);
                        userRepo.save(user);
                        candidateRepo.save(candidate);
                        return "Candidate has been added successfully";
                    }
                    else
                    {
                        return "Invalid pincode!";
                    }
                }
                else
                {
                    return "Invalid phone number";
                }
            }
            else
            {
                return "Invalid email address";
            }
        }
    }

    public String getAllRecruiter()
    {
        List<User> getAllUser = userRepo.findAll();
        List<Recruiter> getAllRec = recruiterRepo.findAll();
        if(getAllUser.size()>0 && getAllRec.size()>0)
        {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j<getAllRec.size(); j++)
            {
                sb.append("|").append("Recruiter ID: ").append(getAllRec.get(j).getUser().getUser_id());
                sb.append("|").append("Name: ").append(getAllRec.get(j).getUser().getFirstName());
                sb.append(" ").append(getAllRec.get(j).getUser().getLastName());
                sb.append("|").append("Email: ").append(getAllRec.get(j).getUser().getEmail());
                sb.append("|").append("Phone: +91-").append(getAllRec.get(j).getUser().getPhone());
                sb.append("|").append("Company: ").append(getAllRec.get(j).getCompany());
                sb.append("|").append("Designation: ").append(getAllRec.get(j).getDesignation());
                sb.append("\n");
            }
            return sb.toString();
        }
        else
        {
            return "No recruiter record available in database";
        }
    }

    public String getRecruiterByEmail(String email)
    {
        if(Validator.validateEmail(email, "Recruiter"))
        {
            Optional<User> getUser = userRepo.findByEmail(email);
            if(getUser.isPresent())
            {
                Long user_id = getUser.get().getUser_id();
                Optional<Recruiter> getRec = recruiterRepo.findById(user_id);
                if (getRec.isPresent())
                {
                    StringBuilder sb = new StringBuilder();
                    sb.append("|").append("Recruiter ID: ").append(getRec.get().getUser().getUser_id());
                    sb.append("|").append("Name: ").append(getRec.get().getUser().getFirstName());
                    sb.append(" ").append(getRec.get().getUser().getLastName());
                    sb.append("|").append("Email: ").append(getRec.get().getUser().getEmail());
                    sb.append("|").append("Phone: +91-").append(getRec.get().getUser().getPhone());
                    sb.append("|").append("Company: ").append(getRec.get().getCompany());
                    sb.append("|").append("Designation: ").append(getRec.get().getDesignation());
                    sb.append("\n");

                    return sb.toString();
                }
            }
            else
            {
                return "No record available with the provided email "+email;
            }
        }
        return "Invalid email address. Please check your email and try again!";
    }

    public String getRecruiterByPhone(String phone)
    {
        if(Validator.validatePhone(phone))
        {
            Optional<User> getUser = userRepo.findByPhone(phone);
            if(getUser.isPresent())
            {
                Long user_id = getUser.get().getUser_id();
                Optional<Recruiter> getRec = recruiterRepo.findById(user_id);
                if (getRec.isPresent())
                {
                    StringBuilder sb = new StringBuilder();
                    sb.append("|").append("Recruiter ID: ").append(getRec.get().getUser().getUser_id());
                    sb.append("|").append("Name: ").append(getRec.get().getUser().getFirstName());
                    sb.append(" ").append(getRec.get().getUser().getLastName());
                    sb.append("|").append("Email: ").append(getRec.get().getUser().getEmail());
                    sb.append("|").append("Phone: +91-").append(getRec.get().getUser().getPhone());
                    sb.append("|").append("Company: ").append(getRec.get().getCompany());
                    sb.append("|").append("Designation: ").append(getRec.get().getDesignation());
                    sb.append("\n");

                    return sb.toString();
                }
            }
            else
            {
                return "No record available with the provided phone number +91-"+phone;
            }
        }
        return "Invalid phone number. Please check your phone number and try again!";
    }
    public String getAllCandidate()
    {
        List<Candidate> getAllCandidate = candidateRepo.findAll();
        if(getAllCandidate.size()>0)
        {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < getAllCandidate.size(); i++)
            {
                sb.append("|").append("CID: ").append(getAllCandidate.get(i).getCID());
                sb.append("|").append("Name: ").append(getAllCandidate.get(i).getUser().getFirstName());
                sb.append(" ").append(getAllCandidate.get(i).getUser().getLastName());
                sb.append("|").append("Email: ").append(getAllCandidate.get(i).getUser().getEmail());
                sb.append("|").append("Phone: +91-").append(getAllCandidate.get(i).getUser().getPhone());
                sb.append("|").append("Current Company: ").append(getAllCandidate.get(i).getCurrentCompany());
                sb.append("|").append("Total years of experience: ").append(getAllCandidate.get(i).getTotalYearsOfExperience());
                sb.append("|").append("Relevant years of experience: ").append(getAllCandidate.get(i).getRelevantYearsOfExperience());
                sb.append("|").append("Primary Skill").append(getAllCandidate.get(i).getPrimarySkill());
                sb.append("|").append("Secondary Skill: ").append(getAllCandidate.get(i).getSecondarySkill());
                sb.append("\n");
            }
            return sb.toString();
        }
        else
        {
            return "No candidate record available in database.";
        }
    }
}
