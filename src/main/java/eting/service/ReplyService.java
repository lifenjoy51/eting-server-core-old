package eting.service;

import eting.domain.Reply;
import eting.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifenjoy51 on 14. 12. 26.
 */
@Service
public class ReplyService {

    @Autowired
    ReplyRepository replyRepository;

    public Reply save(Reply reply){
        return replyRepository.save(reply);
    }

}
