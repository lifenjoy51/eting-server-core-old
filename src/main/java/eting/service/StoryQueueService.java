package eting.service;

import eting.domain.Incognito;
import eting.domain.QueueKey;
import eting.domain.Story;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by lifenjoy51 on 14. 12. 25.
 */
@Component
public class StoryQueueService {

    //main queue
    private Map<QueueKey, Queue<Story>> listMap;

    public StoryQueueService() {
        this.listMap = new HashMap<QueueKey, Queue<Story>>();
    }

    /**
     * insert queue
     * @param story
     */
    public void insert(Story story){
        //generate key.
        QueueKey key = new QueueKey(story);

        //insert into queue.
        if(!this.listMap.containsKey(key))
            this.listMap.put(key, new ConcurrentLinkedQueue<Story>());
        this.listMap.get(key).offer(story);
    }

    /**
     * get story from queue by incognito.
     *
     * @param incognito
     * @return
     */
    public Story get(Incognito incognito){
        //generate key.
        QueueKey key = new QueueKey(incognito);

        //list - available stories.
        Queue<Story> queue = listMap.get(key);

        return queue.remove();
    }

    /**
     * print out all queued stories.
     */
    public void print(){
        System.out.println("## story queue ");
        for(Map.Entry<QueueKey, Queue<Story>> e : listMap.entrySet()){
            System.out.println(e.getKey());
            for(Story s : e.getValue()){
                System.out.println(s);
            }
            System.out.println("---");
        }

    }

}
