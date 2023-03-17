package com.hospitaltask.serviceImpl;

import com.hospitaltask.entity.SuperAdmin;
import com.hospitaltask.repository.SuperAdminRepo;
import com.hospitaltask.service.SuperAdminService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SuperAdminImp implements SuperAdminService {

    SuperAdmin superAdmin = null;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SuperAdminRepo superAdminRepo;
    @Autowired
    private SendEmailTemplate emailTemplate;

    public SuperAdmin enable(long idL, String id) {
        if (idL >= 0 && id.isBlank())
            superAdmin = superAdminRepo.findById(idL).get();
        else
            superAdmin = superAdminRepo.findByEmail(id);

        superAdmin.setFlag(true);
        superAdminRepo.save(superAdmin);
        return superAdmin;
    }

    public SuperAdmin disable(long idL, String id) {
        if (idL > 0 && id == null)
            superAdmin = superAdminRepo.findById(idL).get();
        else
            superAdmin = superAdminRepo.findByEmail(id);
        superAdmin.setFlag(false);
        superAdminRepo.save(superAdmin);
        return superAdmin;
    }

    /*
     * public SuperAdmin dtoToSuperUser(SuperUserDto superUserDto) { SuperAdmin
     * superAdmin = this.modelMapper.map(superUserDto, SuperAdmin.class); return
     * superAdmin; }
     */

    @Override
    public SuperAdmin save(@NotNull SuperAdmin save) {

        save.setPassword(bCryptPasswordEncoder.encode(save.getPassword()));

        /*
         * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         * Object details = auth.getDetails(); System.out.println(details.toString());
         */
        String conformationToken = UUID.randomUUID().toString();
        save.setConfirmationToken(conformationToken);
        String url = "127.0.0.1:8000/super/verify/" + save.getEmail() + "/" + conformationToken;
        save.setIsActive(false);
        // save.setCreatedBy(userDetails.getUsername());
        String myString = "Here's a button: <button type='Submit'>Click me!</button>";
        String message = "Notification Account Activation";
        String obj = "Please verify your email address to get access to your account   " + "\n" + url + "\n"
                + "Thank You ";
        emailTemplate.sendAttached(obj, myString, save.getEmail());

        SuperAdmin superAdmin = superAdminRepo.save(save);
        return superAdmin;
    }

    @Override
    public SuperAdmin update(SuperAdmin data, Long id) {
        SuperAdmin findUser = superAdminRepo.findById(id).orElse(null);
        findUser.setUserName(data.getUserName());
        findUser.setEmail(data.getEmail());
        findUser.setCreatedBy(data.getCreatedBy());
        findUser.setPassword(bCryptPasswordEncoder.encode(data.getPassword()));
        findUser.setLastModifiedDate(data.getLastModifiedDate());
        SuperAdmin saveUpdated = superAdminRepo.save(findUser);
        return saveUpdated;
    }

    @Override
    public SuperAdmin findById(Long id) {
        SuperAdmin superAdmin = superAdminRepo.findById(id).orElse(null);
        return superAdmin;
    }

    // ToDo ....findByEmail .......
    @Override
    public SuperAdmin findByEmail(String email) {
        SuperAdmin superAdmin = superAdminRepo.findByEmail(email);
        return superAdmin;
    }

    @Override
    public List<SuperAdmin> findAll() {
        List<SuperAdmin> superAdmins = superAdminRepo.findAll();
        return superAdmins;
    }

    @Override
    public SuperAdmin disableById(long id) {
        SuperAdmin superAdmin1 = disable(id, null);
        return superAdmin1;
    }

    @Override
    public SuperAdmin enableById(long id) {
        SuperAdmin superAdmin1 = enable(id, null);
        return superAdmin1;
    }

    // TODO ....Delete By Email.....
    @Override
    public SuperAdmin disableByEmail(String id) {
        SuperAdmin superAdmin1 = disable(0, id);
        return superAdmin1;
    }

    @Override
    public SuperAdmin enableByEmail(String id) {
        SuperAdmin superAdmin1 = enable(0, id);
        return superAdmin1;
    }

    @Override
    public List<SuperAdmin> findSuperUserByFlag(Integer id, Boolean aBoolean) {
        return superAdminRepo.findSuperAdminByFlag(id, aBoolean);
    }

    @Override
    public List<SuperAdmin> findSuperAdminByEmailAndFlag(String id, Boolean aBoolean) {
        return superAdminRepo.findSuperAdminByEmailAndFlag(id, aBoolean);
    }

    @Override
    public SuperAdmin acountVerify(String email, String token) {

        SuperAdmin acountVerify = null;
        try {
            acountVerify = superAdminRepo.acountVerify(email, token);
            if (token.equals(acountVerify.getConfirmationToken())) {
                acountVerify.setIsActive(true);
                acountVerify.setConfirmationToken(null);
                superAdminRepo.save(acountVerify);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return acountVerify;
    }

}
