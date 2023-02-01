package com.leij.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.leij.product.model.Product;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface ProductService extends IService<Product> {


    @TwoPhaseBusinessAction(name = "decreaseTcc", commitMethod = "decreaseCommit", rollbackMethod = "decreaseRollback")
    public void decrease(@BusinessActionContextParameter(paramName = "goodsId") Integer goodsId,
                         @BusinessActionContextParameter(paramName = "quantity") Integer quantity);

    public boolean decreaseCommit(BusinessActionContext context);

    public boolean decreaseRollback(BusinessActionContext context);
}
