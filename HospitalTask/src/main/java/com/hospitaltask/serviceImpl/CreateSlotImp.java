package com.hospitaltask.serviceImpl;

import com.hospitaltask.dto.SlotDto;
import com.hospitaltask.entity.SuperSlot;
import com.hospitaltask.entity.Slots;
import com.hospitaltask.repository.CreateSlotRepo;
import com.hospitaltask.repository.SaveSlotRepo;
import com.hospitaltask.service.CreateSlotService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateSlotImp implements CreateSlotService {

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private CreateSlotRepo createSlotRepo;

    @Autowired
    private SaveSlotRepo repo;

    SuperSlot dtoToSlot(SlotDto slotDto)
    {
        return  modelMapper.map(slotDto, SuperSlot.class);
    }


    @Override
    public SuperSlot save(SlotDto slotDto) {
        return createSlotRepo.save(dtoToSlot(slotDto));
    }

    @Override
    public SuperSlot update(Long id, String duration) {
        //TODO....update
        return null;
    }

    @Override
    public SuperSlot updateByStartTime(Long id, String startTime) {
        //TODO....updateByStartTime
        return null;
    }

    @Override
    public SuperSlot updateByEndTime(Long id, String endTime) {
        //TODO....updateByEndTime
        return null;
    }

    @Override
    public SuperSlot updateAll(Long id, String startTime, String endTime, String duration) {
        //TODO updateAll
        return null;
    }

    @Override
    public SuperSlot delete(Long id) {
        //TODO Delete
        createSlotRepo.deleteById(id);
        return null;
    }

    @Override
    public List<SuperSlot> getAll() {
        return createSlotRepo.findAll();
    }



    @Override
    public int createSlot() {

        int slot = 0;
        SuperSlot superSlots = createSlotRepo.getLastInsertedValue();
        System.out.println(superSlots);
        String durationMinitus=superSlots.getDurationMinutes();
        String STARTTIMEDB = superSlots.getStartTime();
        String ENDTIMEDB = superSlots.getEndTime();
        String durationTimeDB = superSlots.getDurationMinutes();

        int startTimeDB = Integer.parseInt(STARTTIMEDB.substring(0, 2));
        int startTimeOriginal = startTimeDB;
        int durationTime = Integer.parseInt(durationTimeDB.substring(0, 2));
        int endTime = Integer.parseInt(ENDTIMEDB.substring(0, 2));
        int totalMinutes = (endTime - startTimeDB) * 60;
        int totalSlot = totalMinutes / durationTime;
        int k = 1, l = 0, hourIncrement = 0;
        int hours = 0;
        int startMinutes = 0;
        int endMinutes = durationTime;
        hours = Integer.parseInt(STARTTIMEDB);
        int oldHours = 0;
        int exext60 = 0;
        for (int i = 0; i < totalSlot; i++) {
            Slots slots = new Slots();

            if (i != 0) {

                if (endMinutes >= durationTime) {
                    startMinutes = endMinutes;
                    endMinutes = durationTime + endMinutes;
                } else {
                    if (endMinutes == 0) {
                        startMinutes = 0;
                    } else {
                        startMinutes = durationTime * l;
                    }
                    endMinutes = durationTime * k;
                }

                if (endMinutes >= 60) {
                    exext60++;
                    if (exext60 != 0 && startMinutes <= 60) {
                        oldHours = hours;
                        if (endMinutes == 60) {
                            hourIncrement++;
                            hours = hours + hourIncrement;
                            endMinutes = 0;
                            slots.setStartTime(String.valueOf(oldHours) + ":" + startMinutes);
                            slots.setEndTime(String.valueOf(hours) + ":" + "0" + endMinutes);
                            k = 1;
                            l = 1;
                            hourIncrement--;
                        } else {
                            slots.setStartTime(String.valueOf(hours) + ":" + 00);
                            slots.setEndTime(String.valueOf(hours) + ":" + String.valueOf(endMinutes));
                        }
                    } else {

                        hourIncrement++;
                        hours = startTimeOriginal + hourIncrement;
                        endMinutes = endMinutes - 60;
                        k = 1;
                        l = 1;
                        if (endMinutes == 0) {
                            slots.setStartTime(String.valueOf(hours) + ":" + "00");
                            oldHours++;
                            endMinutes = endMinutes + durationTime;
                            slots.setEndTime(hours + ":" + endMinutes);
                            k = 1;
                            l = 1;
                        } else {
                            if (endMinutes < 10) {
                                slots.setStartTime(String.valueOf(oldHours) + ":" + startMinutes);
                                oldHours++;
                                slots.setEndTime(hours + ":" + "0" + String.valueOf(endMinutes));
                            } else {
                                slots.setStartTime(String.valueOf(oldHours) + ":" + startMinutes);
                                oldHours++;
                                slots.setEndTime(String.valueOf(hours) + ":" + String.valueOf(endMinutes));
                                k = 1;
                                l = 1;
                            }
                        }
                    }
                } else {
                    if (endMinutes < 10) {
                        if (startMinutes < 10)
                            slots.setStartTime(String.valueOf(hours) + ":" + "0" + String.valueOf(startMinutes));
                        else
                            slots.setStartTime(String.valueOf(hours) + ":" + String.valueOf(startMinutes));
                        slots.setEndTime(String.valueOf(hours) + ":" + String.valueOf(endMinutes));
                        k++;
                        l++;
                    } else {
                        if (startMinutes < 10)
                            slots.setStartTime(String.valueOf(hours) + ":" + "0" + startMinutes);
                        else
                            slots.setStartTime(String.valueOf(hours) + ":" + startMinutes);
                        slots.setEndTime(String.valueOf(hours) + ":" + String.valueOf(endMinutes));
                        k++;
                        l++;
                    }
                }
            } else {
                slots.setStartTime(String.valueOf(hours) + ":" + "00");
                if(endMinutes==60) {
                    hours++;endMinutes=0;
                    slots.setEndTime(String.valueOf(hours) + ":" + String.valueOf(endMinutes+"0"));
                    k++;
                    l++;
                }
                else
                {
                    slots.setEndTime(String.valueOf(hours) + ":" + String.valueOf(endMinutes));
                    k++;
                    l++;
                }
            }
            System.out.println(slots.getStartTime());
            System.out.println(slots.getEndTime());
            slots.setDurationTime(durationMinitus);
            repo.save(slots);
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
