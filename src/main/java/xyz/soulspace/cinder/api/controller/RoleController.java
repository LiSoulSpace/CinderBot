package xyz.soulspace.cinder.api.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import xyz.soulspace.cinder.component.security.DynamicSecurityMetadataSource;
import xyz.soulspace.cinder.api.entity.Resource;
import xyz.soulspace.cinder.api.service.ResourceService;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 前端控制器
 * </p>
 *
 * @author soulspace
 * @since 2022-03-02
 */
@Controller
@CrossOrigin
@RequestMapping("/api/role")
@Tag(name = "角色控制器RoleController")
public class RoleController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @Operation(summary = "添加后台资源")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Resource umsResource) {
        boolean count = resourceService.save(umsResource);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @Operation(summary = "修改后台资源")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable Long id,
                               @RequestBody Resource umsResource) {
        umsResource.setId(id);
        boolean count = resourceService.updateById(umsResource);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @Operation(summary = "根据ID获取资源详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> getItem(@PathVariable Long id) {
        Resource umsResource = resourceService.getById(id);
        return new ResponseEntity<>(umsResource, HttpStatus.OK);
    }

    @Operation(summary = "根据ID删除后台资源")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean count = resourceService.removeById(id);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @Operation(summary = "分页模糊查询后台资源")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> list(@RequestParam(required = false) Long categoryId,
                                                      @RequestParam(required = false) String nameKeyword,
                                                      @RequestParam(required = false) String urlKeyword,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<Resource> resourceList = resourceService.list(categoryId,nameKeyword, urlKeyword, pageSize, pageNum);
        return new ResponseEntity<>(resourceList, HttpStatus.OK);
    }

    @Operation(summary = "查询所有后台资源")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> listAll() {
        List<Resource> resourceList = resourceService.list();
        return new ResponseEntity<>(resourceList, HttpStatus.OK);
    }
}
