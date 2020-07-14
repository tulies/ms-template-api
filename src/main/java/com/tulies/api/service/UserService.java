package com.tulies.api.service;


import com.tulies.api.beans.dto.UserDTO;
import com.tulies.api.beans.form.UserCreateForm;
import com.tulies.api.beans.form.UserUpdateForm;
import com.tulies.api.beans.qo.UserQO;
import com.tulies.api.beans.vo.PageVO;
import com.tulies.api.entity.User;

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

    // 新增
    User create(UserCreateForm userForm);

    User update(UserUpdateForm userForm);
}
