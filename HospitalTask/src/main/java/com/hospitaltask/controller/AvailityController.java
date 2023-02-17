package com.hospitaltask.controller;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.hospitaltask.dto.CreateSlotDto;
import com.hospitaltask.repository.CreateSlotRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitaltask.dto.AvailityDto;
import com.hospitaltask.entity.Availity;
import com.hospitaltask.repository.AvailityRepo;
import com.hospitaltask.response.CustomResponseHandler;

@RestController
@RequestMapping("/availity")

public class AvailityController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AvailityRepo availityRepo;

    @Autowired
    private CreateSlotRepo createSlotRepo;

    Availity dtoToAvality(AvailityDto availityDto) {
        return this.modelMapper.map(availityDto, Availity.class);
    }

    @PostMapping("/show")
    public ResponseEntity<?> availity(@RequestBody @NotNull AvailityDto availityDto) {


        /*Availity save = null;
        try {
            if (availityDto.getStartTime() >= 10 && availityDto.getEndTime() <= 17 && !availityDto.getStartTime().equals(availityDto.getEndTime()) && availityDto.getEndTime() != 0) {
                availityDto.setSlot(oneDaySlot(availityDto.getStartTime(), availityDto.getEndTime()));
                save = availityRepo.save(dtoToAvality(availityDto));
                for (int i = 0; i < 6; i++) {
                    int increment = 1;
                    LocalDate date2 = availityDto.getDate().plusDays(increment);
                    availityDto.setDate(date2);
                    save = availityRepo.save(dtoToAvality(availityDto));

                }

                return CustomResponseHandler.response("Record Saved", HttpStatus.OK, save);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        System.out.println(slotMethod(availityDto));
        return CustomResponseHandler.response("select valid time ", HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    public int oneDaySlot(long STARTTIME, long ENDTIME) {


        if (STARTTIME >= 10 && ENDTIME <= 11)
            return 1;
        else if (STARTTIME >= 10 && ENDTIME <= 11)
            return 2;
        else if (STARTTIME >= 12 && ENDTIME <= 13)
            return 3;
        else if (STARTTIME >= 13 && ENDTIME <= 14)
            return 4;
        else if (STARTTIME >= 14 && ENDTIME <= 15)
            return 5;
        else if (STARTTIME >= 15 && ENDTIME <= 16)
            return 6;
        else if (STARTTIME >= 16 && ENDTIME <= 17)
            return 6;
        else
            return 0;

    }

    int slotMethod(AvailityDto createSlotDto) {

        String startTime = createSlotRepo.getStartTime();
        String endTime = createSlotRepo.getEndTime();
        String duration = createSlotRepo.getDuration();

        System.out.println("startTime   		" + startTime);
        System.out.println("endTime   		" + endTime);
        System.out.println("duration   		" + duration);
        return createSlot(startTime, endTime, duration,createSlotDto);

    }

    int createSlot(String STARTTIME, String ENDTIME, String DURATIONTIME,AvailityDto createSlotDto ) {
        int startTime = Integer.parseInt(STARTTIME.substring(0, 2));
        int durationTime = Integer.parseInt(DURATIONTIME.substring(0, 2));
        int userStartTime=Integer.parseInt(createSlotDto.getStartTime());
        int endTime = Integer.parseInt(ENDTIME.substring(0, 2));
        int totalMinutes = (endTime - startTime) * 60;
        int totalSlot = totalMinutes / durationTime;
        int slot=0;
        for (int i = 0; i < totalSlot; i++) {
            String sTime=String.valueOf(startTime);
			if(totalMinutes>durationTime)
			{


				String eUsersTime=String.valueOf(userStartTime)+":"+durationTime;

                int  sData=0,userTime=0;
                String[] arrOfStr = sTime.split(":", 2);

                for(int j=0;j<arrOfStr.length;j++)
                {
                    int datavalue=Integer.parseInt(arrOfStr[i]);
                    sData=sData+datavalue;
                }
                String[] arrOfStr1 = createSlotDto.getStartTime().split(":", 2);
                for(int j=0;j<arrOfStr1.length;j++)
                {
                    int datavalue=Integer.parseInt(arrOfStr[i]);
                    userTime=userTime+datavalue;
                }

                System.out.println(sData);
                System.out.println(userTime);
                if(sData==userTime)
                {
                    slot++;
                    break;
                }
                else
                {
                    sTime=String.valueOf(startTime)+":"+durationTime;
                    slot++;
                }
			}
            System.out.println(i);
        }
        System.out.println("start time :   " + startTime + "   endTime		" + endTime);
        return slot;
    }

}
