package system.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import system.domain.Conversation;

import java.util.List;

@Repository
public interface ConversationRepository extends CrudRepository<Conversation , Long> {

    List<Conversation> findByReciever(String number);

    List<Conversation> findBySender(String number);
}
