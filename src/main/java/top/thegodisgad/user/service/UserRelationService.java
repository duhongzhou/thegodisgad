package top.thegodisgad.user.service;

import top.thegodisgad.user.entity.UserRelation;
import top.thegodisgad.user.exception.UserException;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_user_relation(关系)】的数据库操作Service
 * @createDate 2022-05-19 16:17:17
 */
public interface UserRelationService {
    // 关注  attention
    // 取消关注  cancel
    // 拉黑  pullBlack
    // 取消拉黑  cancel
    // 获取关系  getRelationship
    /*--------------------------------------------------------*/

    public boolean attention(UserRelation userRelation) throws UserException;
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    public boolean cancelAttention(UserRelation userRelation) throws UserException;
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    public boolean pullBlack(UserRelation userRelation) throws UserException;
    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/

    public boolean cancelPullBlack(UserRelation userRelation) throws UserException;
    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/

    public List<UserRelation> getRelationship(UserRelation userRelation) throws UserException;
    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/

    public boolean changRelation(UserRelation userRelation) throws UserException;
    /*--------------------------------------------------------*/
}
