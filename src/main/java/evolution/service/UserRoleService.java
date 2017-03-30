package evolution.service;

import evolution.common.UserRoleEnum;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 30.03.2017.
 */
@Service
public class UserRoleService {
    public String getRole (long roleId) {
        long userid = UserRoleEnum.USER.getId();
        long adminid = UserRoleEnum.ADMIN.getId();
        if (userid == roleId)
            return UserRoleEnum.USER.toString();
        if (adminid == roleId)
            return UserRoleEnum.ADMIN.toString();
        return null;
    }
}
