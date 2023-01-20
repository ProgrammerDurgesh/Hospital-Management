package com.hospitaltask.serviceImpl;

import com.hospitaltask.dto.SuperUserDto;
import com.hospitaltask.entity.SuperAdmin;
import com.hospitaltask.repository.SuperAdminRepo;
import com.hospitaltask.service.SuperAdminService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuperAdminImp implements SuperAdminService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SuperAdminRepo superAdminRepo;

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
    public Optional<SuperAdmin> disableById(long id) {
        try {
            superAdminRepo.disableById(id);
        }catch (Exception e)
        {

            e.printStackTrace();
        }
        return superAdminRepo.findById(id);
    }

    @Override
    public SuperAdmin enableById(long id) {
            try {
               superAdminRepo.enableById(id);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        return superAdminRepo.findById(id).get();
    }

    //TODO ....Delete By Email.....
    @Override
    public SuperAdmin disableByEmail(String id) {
      superAdminRepo.disableByEmail(id);
        return null;
    }

    @Override
    public SuperAdmin enableByEmail(String id) {
         superAdminRepo.enableByEmail(id);
        return null;
    }
}
