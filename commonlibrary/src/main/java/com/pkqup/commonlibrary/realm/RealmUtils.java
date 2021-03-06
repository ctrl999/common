package com.pkqup.commonlibrary.realm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * @CreatedbBy: liucun on 2018/6/17.
 * @Describe: realm数据库操作工具类
 */
public class RealmUtils {


    /**
     * 增加单条数据到数据库中
     *
     * @param bean 数据对象，必须继承了RealmObject
     */
    public static void add(final RealmObject bean) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(bean);
            }
        });

    }

    /**
     * 增加多条数据到数据库中
     *
     * @param beans 数据集合，其中元素必须继承了RealmObject
     */
    public static void add(final List<? extends RealmObject> beans) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(beans);

            }
        });

    }

    /**
     * 增加多条数据到数据库中
     *
     * @param beans 数据集合，其中元素必须继承了RealmObject
     */
    public static void addAsync(final List<? extends RealmObject> beans) {
        Realm.getDefaultInstance().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(beans);
            }
        });

    }

    /**
     * 删除数据库中clazz类所属所有元素
     */
    public static void deleteAll(Class<? extends RealmObject> clazz) {
        final RealmResults<? extends RealmObject> beans = Realm.getDefaultInstance().where(clazz).findAll();

        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                beans.deleteAllFromRealm();

            }
        });

    }

    /**
     * 删除数据库中clazz类所属所有元素
     */
    public static void deleteAllAsync(Class<? extends RealmObject> clazz) {
        final RealmResults<? extends RealmObject> beans = Realm.getDefaultInstance().where(clazz).findAll();

        Realm.getDefaultInstance().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                beans.deleteAllFromRealm();

            }
        });


    }

    /**
     * 删除数据库中clazz类所属第一个元素
     */
    public static void deleteFirst(Class<? extends RealmObject> clazz) {
        final RealmResults<? extends RealmObject> beans = Realm.getDefaultInstance().where(clazz).findAll();
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                beans.deleteFirstFromRealm();

            }
        });

    }

    /**
     * 删除数据库中clazz类所属最后一个元素
     */
    public static void deleteLast(Class<? extends RealmObject> clazz) {
        final RealmResults<? extends RealmObject> beans = Realm.getDefaultInstance().where(clazz).findAll();

        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                beans.deleteLastFromRealm();

            }
        });

    }

    /**
     * 删除数据库中clazz类所属数据中某一位置的元素
     */
    public static void deleteElement(Class<? extends RealmObject> clazz, final int position) {
        final RealmResults<? extends RealmObject> beans = Realm.getDefaultInstance().where(clazz).findAll();

        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                beans.deleteFromRealm(position);

            }
        });

    }

    /**
     * 查询数据库中clazz类所属所有数据
     */
    public static List<? extends RealmObject> queryAll(Class<? extends RealmObject> clazz) {
        final RealmResults<? extends RealmObject> beans = Realm.getDefaultInstance().where(clazz).findAll();
        return Realm.getDefaultInstance().copyFromRealm(beans);
    }

    /**
     * 查询数据库中clazz类所属所有数据
     */
    public static RealmResults<? extends RealmObject> queryAllAsync(Class<? extends RealmObject> clazz) {
        final RealmResults<? extends RealmObject> beans = Realm.getDefaultInstance().where(clazz).findAllAsync();

        return beans;
    }

    /**
     * 查询数据，按增量排序
     */
    public static List<? extends RealmObject> queryAllByAscending(Class<? extends RealmObject> clazz, String fieldName) {
        RealmResults<? extends RealmObject> beans = Realm.getDefaultInstance().where(clazz).findAll();
        RealmResults<? extends RealmObject> results = beans.sort(fieldName, Sort.ASCENDING);
        return Realm.getDefaultInstance().copyFromRealm(results);
    }


    /**
     * 查询数据，按降量排序
     */
    public static List<? extends RealmObject> queryAllByDescending(Class<? extends RealmObject> clazz, String fieldName) {
        RealmResults<? extends RealmObject> beans = Realm.getDefaultInstance().where(clazz).findAll();
        RealmResults<? extends RealmObject> results = beans.sort(fieldName, Sort.DESCENDING);
        return Realm.getDefaultInstance().copyFromRealm(results);
    }

    /**
     * 查询满足条件的第一个数据
     */
    public static RealmObject queryByFieldFirst(Class<? extends RealmObject> clazz, String fieldName, String value) throws NoSuchFieldException {

        final RealmObject bean = Realm.getDefaultInstance().where(clazz).equalTo(fieldName, value).findFirst();

        return bean;
    }

    /**
     * 查询满足条件的所有数据
     */
    public static RealmResults<? extends RealmObject> queryByFieldAll(Class<? extends RealmObject> clazz, String fieldName, String value) throws NoSuchFieldException {

        final RealmResults<? extends RealmObject> beans = Realm.getDefaultInstance().where(clazz).equalTo(fieldName, value).findAll();

        return beans;
    }

    /**
     */
    public static RealmResults<? extends RealmObject> queryByFieldAllAsync(Class<? extends RealmObject> clazz, String fieldName, String value) throws NoSuchFieldException {

        final RealmResults<? extends RealmObject> beans = Realm.getDefaultInstance().where(clazz).equalTo(fieldName, value).findAllAsync();

        return beans;
    }

    /**
     * 查询满足条件的第一个数据
     */
    public static RealmObject queryByFieldFirst(Class<? extends RealmObject> clazz, String fieldName, int value) throws NoSuchFieldException {
        final RealmObject bean = Realm.getDefaultInstance().where(clazz).equalTo(fieldName, value).findFirst();

        return bean;
    }

    /**
     * 查询满足条件的所有数据
     */
    public static RealmResults<? extends RealmObject> queryByFieldAll(Class<? extends RealmObject> clazz, String fieldName, int value) throws NoSuchFieldException {
        final RealmResults<? extends RealmObject> beans =
                Realm.getDefaultInstance().where(clazz).equalTo(fieldName, value).findAll();
        return beans;
    }

    /**
     * 查询满足条件的所有数据
     */
    public static RealmResults<? extends RealmObject> queryByFieldAllAsync(Class<? extends RealmObject> clazz, String fieldName, int value) throws NoSuchFieldException {

        final RealmResults<? extends RealmObject> beans = Realm.getDefaultInstance().where(clazz).equalTo(fieldName, value).findAllAsync();
        return beans;
    }



    /**
     * 更新满足某个条件的第一个数据的属性值
     */
    public static void updateFirstByField(Class<? extends RealmObject> clazz, String fieldName, String oldValue, String newValue) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final RealmObject bean = Realm.getDefaultInstance().where(clazz).equalTo(fieldName, oldValue).findFirst();
        Realm.getDefaultInstance().beginTransaction();
        Method method = clazz.getMethod(fieldName, String.class);
        method.invoke(bean, newValue);
        Realm.getDefaultInstance().commitTransaction();

    }

    /**
     * 更新满足某个条件的第一个数据的属性值
     */
    public static void updateFirstByField(Class<? extends RealmObject> clazz, String fieldName, int oldValue, int newValue) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final RealmObject bean = Realm.getDefaultInstance().where(clazz).equalTo(fieldName, oldValue).findFirst();
        Realm.getDefaultInstance().beginTransaction();
        Method method = clazz.getMethod(fieldName, int.class);
        method.invoke(bean, newValue);
        Realm.getDefaultInstance().commitTransaction();

    }

    /**
     * 更新满足某个条件的第一个数据的属性值
     */
    public static void updateAllByField(Class<? extends RealmObject> clazz, String fieldName, String oldValue, String newValue) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final RealmResults<? extends RealmObject> beans = Realm.getDefaultInstance().where(clazz).equalTo(fieldName, oldValue).findAll();
        Realm.getDefaultInstance().beginTransaction();
        Method method = clazz.getMethod(fieldName, String.class);
        for (int i = 0; i < beans.size(); i++) {
            RealmObject realmObject = beans.get(i);
            method.invoke(realmObject, newValue);
        }
        Realm.getDefaultInstance().commitTransaction();

    }

    /**
     * 更新满足某个条件的第一个数据的属性值
     */
    public static void updateAllByField(Class<? extends RealmObject> clazz, String fieldName, int oldValue, int newValue) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final RealmResults<? extends RealmObject> beans = Realm.getDefaultInstance().where(clazz).equalTo(fieldName, oldValue).findAll();
        Realm.getDefaultInstance().beginTransaction();
        Method method = clazz.getMethod(fieldName, int.class);
        for (int i = 0; i < beans.size(); i++) {
            RealmObject realmObject = beans.get(i);
            method.invoke(realmObject, newValue);
        }
        Realm.getDefaultInstance().commitTransaction();
    }

}
