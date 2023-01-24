package com.hospitaltask.serviceImpl;

import com.hospitaltask.dto.SuperUserDto;
import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.SuperAdmin;
import com.hospitaltask.repository.SuperAdminRepo;
import com.hospitaltask.service.SuperAdminService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperAdminImp implements SuperAdminService {

    SuperAdmin superAdmin = null;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SuperAdminRepo superAdminRepo;

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

    public SuperAdmin dtoToSuperUser(SuperUserDto superUserDto) {
        SuperAdmin superAdmin = this.modelMapper.map(superUserDto, SuperAdmin.class);
        return superAdmin;
    }

    @Override
    public SuperAdmin save(@NotNull SuperUserDto save) {

        save.setPassword(bCryptPasswordEncoder.encode(save.getPassword()));
        SuperAdmin superAdmin = superAdminRepo.save(dtoToSuperUser(save));
        return superAdmin;
    }

    // Todo ......Updating Super user
    @Override
    public SuperAdmin update(SuperAdmin data, Long id) {
        SuperAdmin findUser = superAdminRepo.findById(id).orElse(null);
       /* SuperAdmin save=null;
        findUser.Set*/
        return findUser;
    }

    @Override
    public SuperAdmin findById(Long id) {
        SuperAdmin superAdmin = superAdminRepo.findById(id).orElse(null);
        return superAdmin;
    }

    //ToDo ....findByEmail .......
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

    //TODO ....Delete By Email.....
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
}
