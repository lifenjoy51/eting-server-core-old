package eting.queue;

import eting.domain.Incognito;
import eting.domain.Story;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by lifenjoy51 on 14. 12. 25.
 */
@Component
public class StoryQueue {

    //main queue
    Map<QueueKey, List<Story>> listMap;

    /**
     * insert queue
     * @param story
     */
    public void insert(Story story){
        //generate key.
        QueueKey key = generateKey(story);

        //insert into queue.
        if(!this.listMap.containsKey(key))
            this.listMap.put(key, new LinkedList<Story>());
        this.listMap.get(key).add(story);
    }

    /**
     * get story from queue by incognito.
     *
     * @param incognito
     * @return
     */
    public Story get(Incognito incognito){
        return null;
    }

    /**
     * print out all queued stories.
     */
    public void print(){
        for(Map.Entry<QueueKey, List<Story>> e : listMap.entrySet()){
            System.out.println(e.getKey());
            for(Story s : e.getValue()){
                System.out.println(s);
            }
            System.out.println("---");
        }

    }

    /**
     * generate key by given story.
     *
     * @param story
     * @return
     */
    private QueueKey generateKey(Story story) {
        return null;
    }

}
