package top.thegodisgad.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.thegodisgad.user.entity.UserRelation;
import top.thegodisgad.user.exception.UserException;
import top.thegodisgad.user.exception.UserExceptionEnum;
import top.thegodisgad.user.mapper.UserRelationMapper;
import top.thegodisgad.user.service.UserRelationService;
import top.thegodisgad.user.service.UserService;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_user_relation(关系)】的数据库操作Service实现
 * @createDate 2022-05-19 16:17:17
 */
@Service
public class UserRelationServiceImpl
        implements UserRelationService {
    UserService userService;
    UserRelationMapper userRelationMapper;
    @Autowired
    public UserRelationServiceImpl(UserService userService, UserRelationMapper userRelationMapper) {
        this.userService = userService;
        this.userRelationMapper = userRelationMapper;
    }




    private boolean checkUserRelation(UserRelation userRelation) {
        return checkUserRelation(userRelation.getUserId(), userRelation.getFriendId());
    }

    private boolean checkUserRelation(final Long userid, final Long friendId) throws UserException {

        return checkUserId(userid) && checkFriendId(friendId);

    }

    private boolean checkUserId(Long userId) {
        if (userId != null) {
            if (userId != 0) {
                if(userService.itExists(userId)){
                    return true;
                }else{
                    throw new UserException(UserExceptionEnum.NON_EXISTENT_USERID);
                }
            } else {
                throw new UserException(UserExceptionEnum.USERID_ISEMPTY);
            }
        } else {
            throw new UserException(UserExceptionEnum.USERID_ISEMPTY);
        }

    }

    private boolean checkFriendId(Long friendId) {
        if (friendId != null) {
            if (friendId != 0) {
                if(userService.itExists(friendId)){
                    return true;
                }else{
                    throw new UserException(UserExceptionEnum.NON_EXISTENT_USERID);
                }
            } else {
                throw new UserException(UserExceptionEnum.USERID_ISEMPTY_FRIEND);
            }
        } else {
            throw new UserException(UserExceptionEnum.USERID_ISEMPTY_FRIEND);
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean attention(UserRelation userRelation) throws UserException {
        checkUserRelation(userRelation);
        userRelation.setIsShield(0);
        return changRelation(userRelation);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changRelation(UserRelation userRelation) throws UserException{
        userRelation.setIsValid(1);
        UserRelation userRelation1 = userRelationMapper.selectByUserIdAndFriendId(userRelation.getUserId(), userRelation.getFriendId());
        int i=0;
        if(userRelation1!=null)
        {
            i+=userRelationMapper.update(userRelation);
        }else{
            i+=userRelationMapper.insert(userRelation);
        }
        if(i!=1){
            throw new UserException(UserExceptionEnum.UNKNOWN_ERROR);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelAttention(UserRelation userRelation) throws UserException {
        checkUserRelation(userRelation);
        userRelation.setIsValid(0);
        if(userRelationMapper.update(userRelation)!=1)
        {
            throw new UserException(UserExceptionEnum.UNKNOWN_ERROR);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean pullBlack(UserRelation userRelation) throws UserException {
        checkUserRelation(userRelation);
        userRelation.setIsShield(1);
        return changRelation(userRelation);
    }

    @Override
    public boolean cancelPullBlack(UserRelation userRelation) throws UserException {
        checkUserRelation(userRelation);
        userRelation.setIsValid(0);
        if(userRelationMapper.update(userRelation)!=1)
        {
            throw new UserException(UserExceptionEnum.UNKNOWN_ERROR);
        }
        return true;
    }

    @Override
    public List<UserRelation> getRelationship(UserRelation userRelation) throws UserException {
        checkUserId(userRelation.getUserId());
        return userRelationMapper.selectByUserId(userRelation.getUserId());
    }
}




