package eting.service;

import eting.domain.Device;
import eting.domain.Incognito;
import eting.repository.DeviceRepository;
import eting.repository.IncognitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by lifenjoy51 on 14. 12. 19.
 */
@Service
public class IncognitoService {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    IncognitoRepository incognitoRepository;

    /**
     * insert new device and incognito.
     * @param device
     * @return
     */
    @Transactional
    public Incognito registration(Device device){

        //save device info
        deviceRepository.save(device);

        //generate new incognito by device
        Incognito incognito = new Incognito(device);

        //save incognito
        incognitoRepository.save(incognito);

        return incognito;
    }

    /**
     * get incognito info.
     * @param incognitoId
     * @return
     */
    public Incognito get(long incognitoId){
        return incognitoRepository.findOne(incognitoId);
    }

    /**
     * update incognito info.
     * @param incognito
     */
    public void update(Incognito incognito){
        incognitoRepository.save(incognito);
    }

}
