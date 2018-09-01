package learning.springboot.springBootTest.topic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TopicService {
	private List<Topic> topics;
	public TopicService() {
		topics = new ArrayList<Topic>();
		topics.add(new Topic(1,"Java","Desc 1"));
		topics.add(new Topic(2,"Rest API","Desc 2"));
		topics.add(new Topic(3,"Spring Boot","Desc 3"));
	}
	public List<Topic> getAllTopics() {
		return topics;
	}
	public Topic getTopic(int id) {
		for (Topic topic:topics) {
			if(topic.getId()==id) {
				return topic;
			}
		}
		return null;
	}
	public void addTopic(Topic topic) {
		topics.add(topic);
	}
	public void updateTopic(int id, Topic topic) {
		for(int i=0;i<topics.size();i++) {
			if(topics.get(i).getId()==id) {
				topics.set(i,topic);
			}
		}
	}
	public void deleteTopic(int id) {
		for(int i=0;i<topics.size();i++) {
			if(topics.get(i).getId()==id) {
				topics.remove(i);
			}
		}
	}
}
