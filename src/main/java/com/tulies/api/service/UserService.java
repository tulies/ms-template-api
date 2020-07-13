package com.tulies.blog.service;

import com.tulies.blog.dto.UserDTO;
import com.tulies.blog.entity.User;
import com.tulies.blog.qo.UserQO;
import com.tulies.blog.vo.PageVO;

/**
 * @author 王嘉炀
 * @date 2019-10-12 00:07
 */
public interface UserService {
    PageVO<User> findList(Integer pageNum, Integer pageSize, UserQO userQO, String sorter);
    User findById(Integer id);
    void deleteById(Integer id);
    //上下线
    void changeStatus(Integer id, Integer status);
    UserDTO login(UserQO userQO);
    UserDTO findByUserToken(String userToken);

}
