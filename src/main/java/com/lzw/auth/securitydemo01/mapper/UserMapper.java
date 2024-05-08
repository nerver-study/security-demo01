package com.lzw.auth.securitydemo01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzw.auth.securitydemo01.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}