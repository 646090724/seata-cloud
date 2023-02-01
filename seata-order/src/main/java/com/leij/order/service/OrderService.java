package com.leij.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.leij.order.model.Order;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface OrderService extends IService<Order> {

    void addOrder(String name);

    void addOrder1(String name);

    void addOrder2(String name);


    /**
     * @TwoPhaseBusinessAction 描述⼆阶段提交
     * name: 为 tcc⽅法的 bean 名称，需要全局唯⼀，⼀般写⽅法名即可
     * commitMethod: Commit⽅法的⽅法名
     * rollbackMethod:Rollback⽅法的⽅法名
     * @BusinessActionContextParamete 该注解⽤来修饰 Try⽅法的⼊参，
     * 被修饰的⼊参可以在 Commit ⽅法和 Rollback ⽅法中通过
    BusinessActionContext 获取。
     */
    @TwoPhaseBusinessAction(name = "addTcc", commitMethod = "addCommit", rollbackMethod = "addRollBack")
    public void add(@BusinessActionContextParameter(paramName = "order") Order order);

    public boolean addCommit(BusinessActionContext context);

    public boolean addRollBack(BusinessActionContext context);

}
