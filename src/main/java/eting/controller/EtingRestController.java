package eting.controller;

import eting.domain.Device;
import eting.repository.DeviceRepository;
import eting.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lifenjoy51 on 12/3/14.
 */
@RestController
public class EtingRestController {

    @Autowired
    DeviceRepository deviceRepository;


    // 기기정보 등록
    @RequestMapping(value = "/device", method = RequestMethod.POST)
    public Device registration(@ModelAttribute Device device, BindingResult error) {
        System.out.println(error);
        System.out.println(device);
        deviceRepository.save(device);
        return device;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = Util.getDtFormat();
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
