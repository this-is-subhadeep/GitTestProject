package learning.springboot.springBootTest.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
	@Autowired
	private TopicService topicService;
	@RequestMapping("topics")
	public List<Topic> getTopics() {
		return topicService.getAllTopics();
	}
	@RequestMapping("topic/{id}")
	public Topic getTopic(@PathVariable int id) {
		return topicService.getTopic(id);
	}
	@RequestMapping(method=RequestMethod.POST,value="topics")
	public void addTopic(@RequestBody Topic topic) {
		topicService.addTopic(topic);
	}
	@RequestMapping(method=RequestMethod.PUT,value="topics/{id}")
	public void updateTopic(@PathVariable int id, @RequestBody Topic topic) {
		topicService.updateTopic(id, topic);
	}
	@RequestMapping(method=RequestMethod.DELETE,value="topics/{id}")
	public void deleteTopic(@PathVariable int id) {
		topicService.deleteTopic(id);
	}
}
