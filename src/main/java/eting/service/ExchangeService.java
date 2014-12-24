package eting.service;

import eting.code.ExchangeStatus;
import eting.domain.Device;
import eting.domain.Exchange;
import eting.domain.Incognito;
import eting.domain.Story;
import eting.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifenjoy51 on 14. 12. 24.
 */
@Service
public class ExchangeService {

    @Autowired
    ExchangeRepository exchangeRepository;

    /**
     * insert story to exchange queue.
     * @param story
     * @param incognito
     */
    public void insertQueue(Story story, Incognito incognito){
        Exchange exchange = new Exchange(story, incognito, ExchangeStatus.INSERT_QUEUE);
        exchangeRepository.save(exchange);
    }

}
