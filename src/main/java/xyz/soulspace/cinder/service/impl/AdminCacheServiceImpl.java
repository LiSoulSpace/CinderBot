package xyz.soulspace.cinder.service.impl;

import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.soulspace.cinder.api.entity.Admin;
import xyz.soulspace.cinder.api.entity.AdminRoleRelation;
import xyz.soulspace.cinder.api.entity.Resource;
import xyz.soulspace.cinder.api.mapper.AdminRoleRelationMapper;
import xyz.soulspace.cinder.api.service.AdminService;
import xyz.soulspace.cinder.api.service.impl.AdminServiceImp;
import xyz.soulspace.cinder.service.AdminCacheService;
import xyz.soulspace.cinder.service.RedisService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminCacheServiceImpl implements AdminCacheService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private AdminRoleRelationMapper adminRoleRelationMapper;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;

    @Override
    public void delAdmin(Long adminId) {
        AdminService adminService = new AdminServiceImp();
        Admin admin = adminService.getById(adminId);
        if (admin != null) {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
            redisService.del(key);
        }
    }

    @Override
    public void delResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.del(key);
    }

    @Override
    public void delResourceListByRole(Long roleId) {

        List<AdminRoleRelation> relationList = adminRoleRelationMapper.selectByRoleId(roleId);
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getAdminId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {
        List<AdminRoleRelation> relationList = adminRoleRelationMapper.selectByRoleIds(roleIds);
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getAdminId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public void delResourceListByResource(Long resourceId) {
        List<Long> adminIdList = adminRoleRelationMapper.getAdminIdList(resourceId);
        if (CollUtil.isNotEmpty(adminIdList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = adminIdList.stream().map(adminId -> keyPrefix + adminId).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public Admin getAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return (Admin) redisService.get(key);
    }

    @Override
    public void setAdmin(Admin admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
        redisService.set(key, admin, REDIS_EXPIRE);
    }

    @Override
    public List<Resource> getResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        return (List<Resource>) redisService.get(key);
    }

    @Override
    public void setResourceList(Long adminId, List<Resource> resourceList) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.set(key, resourceList, REDIS_EXPIRE);
    }
}
