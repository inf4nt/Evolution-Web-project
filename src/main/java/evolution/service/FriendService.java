package evolution.service;

import evolution.dao.FriendsDao;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 31.03.2017.
 */
@Service
public class FriendService {


    public void action(String action, long id1, long id2){
        if (action.equals("accept-friend"))
            acceptFriend(id1, id2);
    }


    public void acceptFriend(long id1, long id2){
        if (checkAcceptFriend(id1, id2))
            return;
        friendsDao.acceptFriend(id1, id2);
    }

    public boolean checkAcceptFriend (long id1, long id2){
        return friendsDao.checkIsFriend(id1, id2);
    }


    @Autowired
    private FriendsDao friendsDao;
}
