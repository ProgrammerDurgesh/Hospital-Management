package com.hospitaltask.controller;

import javax.validation.constraints.NotNull;

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
import com.hospitaltask.entity.CreateSlot;
import com.hospitaltask.entity.SaveSlot;
import com.hospitaltask.repository.AvailityRepo;
import com.hospitaltask.repository.CreateSlotRepo;
import com.hospitaltask.repository.SaveSlotRepo;
import com.hospitaltask.response.CustomResponseHandler;

@RestController
@RequestMapping("/availity")

public class AvailityController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AvailityRepo availityRepo;

    @Autowired
    private SaveSlotRepo repo;

    @Autowired
    private CreateSlotRepo createSlotRepo;

    Availity dtoToAvality(AvailityDto availityDto) {
        return this.modelMapper.map(availityDto, Availity.class);
    }

    @PostMapping("/show")
    public ResponseEntity<?> availity(@RequestBody @NotNull AvailityDto availityDto) {

        /*
         * Availity save = null; try { if (availityDto.getStartTime() >= 10 &&
         * availityDto.getEndTime() <= 17 &&
         * !availityDto.getStartTime().equals(availityDto.getEndTime()) &&
         * availityDto.getEndTime() != 0) {
         * availityDto.setSlot(oneDaySlot(availityDto.getStartTime(),
         * availityDto.getEndTime())); save =
         * availityRepo.save(dtoToAvality(availityDto)); for (int i = 0; i < 6; i++) {
         * int increment = 1; LocalDate date2 =
         * availityDto.getDate().plusDays(increment); availityDto.setDate(date2); save =
         * availityRepo.save(dtoToAvality(availityDto));
         *
         * }
         *
         * return CustomResponseHandler.response("Record Saved", HttpStatus.OK, save); }
         * } catch (Exception e) { e.printStackTrace(); }
         */
        System.out.println(slotMethod(availityDto));
        return CustomResponseHandler.response("select valid time ", HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    public int oneDaySlot(long STARTTIME, long ENDTIME) {

        if (STARTTIME >= 10 && ENDTIME <= 11) return 1;
        else if (STARTTIME >= 10 && ENDTIME <= 11) return 2;
        else if (STARTTIME >= 12 && ENDTIME <= 13) return 3;
        else if (STARTTIME >= 13 && ENDTIME <= 14) return 4;
        else if (STARTTIME >= 14 && ENDTIME <= 15) return 5;
        else if (STARTTIME >= 15 && ENDTIME <= 16) return 6;
        else if (STARTTIME >= 16 && ENDTIME <= 17) return 6;
        else return 0;
    }

    int slotMethod(AvailityDto createSlotDto) {
        String startTime = createSlotRepo.getStartTime();
        String endTime = createSlotRepo.getEndTime();
        String duration = createSlotRepo.getDuration();
        System.out.println("startTime   		" + startTime);
        System.out.println("endTime   		" + endTime);
        System.out.println("duration   		" + duration);
        return createSlot(createSlotDto);
    }

    int createSlot(AvailityDto createSlotDto) {

        int slot = 0;
        CreateSlot createSlots = createSlotRepo.getLastInsertedValue();
        System.out.println(createSlots);
        String STARTTIMEDB = createSlots.getStartTime();
        String ENDTIMEDB = createSlots.getEndTime();
        String dTimeDB = createSlots.getDurationMinutes();

        int sTime = Integer.parseInt(STARTTIMEDB.substring(0, 2));
        int sTimeCopy = sTime;
        int dTime = Integer.parseInt(dTimeDB.substring(0, 2));
        int endTime = Integer.parseInt(ENDTIMEDB.substring(0, 2));
        int totalMinutes = (endTime - sTime) * 60;
        int totalSlot = totalMinutes / dTime;
        int k = 1, l = 0, m = 1, n = 1, hourIncrement = 0;
        String startTimeSave = createSlots.getStartTime();
        int minutessave = 0;
        int sTimeSave = 0;

        sTimeSave = Integer.parseInt(startTimeSave);

        // Logic using drop-down index
        for (int i = 0; i < totalSlot; i++) {
            SaveSlot saveSlot = new SaveSlot();
            if (i != 0) {
//Minutes check control TODO 

                int checkEndTime = dTime * k;
                int TimeSave = dTime * l;

                if (checkEndTime >= 60) {
                    hourIncrement++;
                    sTimeSave = sTimeCopy + hourIncrement;
                    System.out.println(sTimeSave + "11");
                    int minutes = checkEndTime - 60;
                    minutessave = dTime + minutes;
                    k = m;
                    l = n;

                    if (minutes == 0) {

                        saveSlot.setStartTime(String.valueOf(sTimeSave) + ":" + "00");
                        saveSlot.setEndTime(sTimeSave + ":" + String.valueOf(minutessave));
                    } else {
                        if (minutes < 10) {

                            saveSlot.setStartTime(String.valueOf(sTimeSave) + ":" + "0" + minutes);
                            saveSlot.setEndTime(sTimeSave + ":" + String.valueOf(minutessave));

                        } else {
                            saveSlot.setStartTime(String.valueOf(sTimeSave) + ":" + minutes);
                            saveSlot.setEndTime(String.valueOf(sTimeSave) + ":" + String.valueOf(minutessave));
                        }
                    }

                } else {

                    saveSlot.setStartTime(String.valueOf(sTimeSave) + ":" + String.valueOf(TimeSave));
                    int endTimeSave = (dTime * k);
                    saveSlot.setEndTime(String.valueOf(sTimeSave) + ":" + String.valueOf(endTimeSave));
                    k = k + 1;
                    l = l + 1;

                }
            } else {

                saveSlot.setStartTime(String.valueOf(startTimeSave) + ":" + "00");
                saveSlot.setEndTime(String.valueOf(sTimeSave) + ":" + String.valueOf(dTime));
                k = k + 1;
                l = l + 1;

            }
            System.out.println(saveSlot.getStartTime());
            System.out.println(saveSlot.getEndTime());
            repo.save(saveSlot);

        }

        int userSTime = 0;

        String[] arrOfStr1 = createSlotDto.getStartTime().split(":", 2);
        for (int j = 0; j < arrOfStr1.length; j++) {

            int datavalue = Integer.parseInt(arrOfStr1[j]);
            userSTime = userSTime + datavalue;
        }

        for (int i = 0; i < totalSlot; i++) {
            if (sTime == userSTime) {
                slot++;
                break;
            } else {

                sTime = sTime + Integer.parseInt(dTimeDB);
                String durtationHours = String.valueOf(sTime);
                int minute = Integer.parseInt(durtationHours.substring(durtationHours.length() - 2, durtationHours.length()));

                /*
                 * if (minute >= 60) { sTimeCopy1 = sTimeCopy1 + 1; sTime = sTimeCopy1; }
                 *
                 * slot++;
                 */
            }
        }

        /*
         * int startTime = Integer.parseInt(STARTTIMEDB.substring(0, 2)); int
         * durationTime = Integer.parseInt(DURATIONTIMEDB.substring(0, 2)); int endTime
         * = Integer.parseInt(ENDTIMEDB.substring(0, 2)); int totalMinutes = (endTime -
         * startTime) * 60; int totalSlot = totalMinutes / durationTime; int
         * userStartTime = Integer.parseInt(createSlotDto.getStartTime().substring(0,
         * 2)); int slot = 0; for (int i = 0; i < totalSlot; i++) { String sTime =
         * String.valueOf(startTime); if (totalMinutes > durationTime) { String
         * eUsersTime = String.valueOf(userStartTime) + ":" + durationTime; int sData =
         * 0, userTime = 0; String[] arrOfStr = sTime.split(":", 2); for (int j = 0; j <
         * arrOfStr.length; j++) { int datavalue = Integer.parseInt(arrOfStr[i]); sData
         * = sData + datavalue; } String[] arrOfStr1 =
         * createSlotDto.getStartTime().split(":", 2); for (int j = 0; j <
         * arrOfStr1.length; j++) { int datavalue = Integer.parseInt(arrOfStr[i]);
         * userTime = userTime + datavalue; } System.out.println(sData);
         * System.out.println(userTime); if (sData == userTime) { slot++; break; } else
         * { sTime = String.valueOf(startTime) + ":" + durationTime; slot++; } }
         * System.out.println(i); } System.out.println("start time :   " + startTime +
         * "   endTime		" + endTime);
         */
        System.out.println(slot);
        return slot;
    }
}
