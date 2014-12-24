package eting.queue;

import eting.domain.Incognito;
import eting.domain.Story;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by lifenjoy51 on 14. 12. 25.
 */
@Component
public class StoryQueue {

    //main queue
    Map<QueueKey, Queue<Story>> listMap;

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
        for(Map.Entry<QueueKey, Queue<Story>> e : listMap.entrySet()){
            System.out.println(e.getKey());
            for(Story s : e.getValue()){
                System.out.println(s);
            }
            System.out.println("---");
        }

    }

}
