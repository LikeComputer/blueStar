package me.tatarka.bindingcollectionadapter2.collections;

/**
 * @another 江祖赟
 * @date 2018/1/3 0003.
 */
public class SimpleRecvDataDiff implements IRecvDataDiff {
    @Override
    public boolean areItemsTheSame(IRecvDataDiff oldData, IRecvDataDiff newData){
        return oldData == newData;
    }

    @Override
    public boolean areContentsTheSame(IRecvDataDiff oldData, IRecvDataDiff newData){
        return true;
    }

    @Override
    public Object getChangePayload(IRecvDataDiff oldData, IRecvDataDiff newData){
        return null;
    }
}
