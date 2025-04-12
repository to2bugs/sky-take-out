package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);



    /**
     * 新增员工数据
     * @param employee
     */
    @AutoFill(OperationType.INSERT)
    void insert(Employee employee);




    /**
     * 分页查询员工数据
     * @param employeePageQueryDTO
     * @return
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 根据id启用禁用员工账号
     *
     */
    @AutoFill(OperationType.UPDATE)
    void update(Employee employee);


    /**
     * 根据id查询员工
     */
    Employee getById(Long id);
}
