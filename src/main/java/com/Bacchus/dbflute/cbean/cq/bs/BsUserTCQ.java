package com.Bacchus.dbflute.cbean.cq.bs;

import java.util.Map;

import org.dbflute.cbean.*;
import org.dbflute.cbean.chelper.*;
import org.dbflute.cbean.coption.*;
import org.dbflute.cbean.cvalue.ConditionValue;
import org.dbflute.cbean.sqlclause.SqlClause;
import org.dbflute.exception.IllegalConditionBeanOperationException;
import com.Bacchus.dbflute.cbean.cq.ciq.*;
import com.Bacchus.dbflute.cbean.*;
import com.Bacchus.dbflute.cbean.cq.*;

/**
 * The base condition-query of user_t.
 * @author DBFlute(AutoGenerator)
 */
public class BsUserTCQ extends AbstractBsUserTCQ {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected UserTCIQ _inlineQuery;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BsUserTCQ(ConditionQuery referrerQuery, SqlClause sqlClause, String aliasName, int nestLevel) {
        super(referrerQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                 InlineView/OrClause
    //                                                                 ===================
    /**
     * Prepare InlineView query. <br>
     * {select ... from ... left outer join (select * from user_t) where FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #CC4747">inline()</span>.setFoo...;
     * </pre>
     * @return The condition-query for InlineView query. (NotNull)
     */
    public UserTCIQ inline() {
        if (_inlineQuery == null) { _inlineQuery = xcreateCIQ(); }
        _inlineQuery.xsetOnClause(false); return _inlineQuery;
    }

    protected UserTCIQ xcreateCIQ() {
        UserTCIQ ciq = xnewCIQ();
        ciq.xsetBaseCB(_baseCB);
        return ciq;
    }

    protected UserTCIQ xnewCIQ() {
        return new UserTCIQ(xgetReferrerQuery(), xgetSqlClause(), xgetAliasName(), xgetNestLevel(), this);
    }

    /**
     * Prepare OnClause query. <br>
     * {select ... from ... left outer join user_t on ... and FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #CC4747">on()</span>.setFoo...;
     * </pre>
     * @return The condition-query for OnClause query. (NotNull)
     * @throws IllegalConditionBeanOperationException When this condition-query is base query.
     */
    public UserTCIQ on() {
        if (isBaseQuery()) { throw new IllegalConditionBeanOperationException("OnClause for local table is unavailable!"); }
        UserTCIQ inlineQuery = inline(); inlineQuery.xsetOnClause(true); return inlineQuery;
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====
    protected ConditionValue _userId;
    public ConditionValue xdfgetUserId()
    { if (_userId == null) { _userId = nCV(); }
      return _userId; }
    protected ConditionValue xgetCValueUserId() { return xdfgetUserId(); }

    public Map<String, EntryTCQ> xdfgetUserId_ExistsReferrer_EntryTList() { return xgetSQueMap("userId_ExistsReferrer_EntryTList"); }
    public String keepUserId_ExistsReferrer_EntryTList(EntryTCQ sq) { return xkeepSQue("userId_ExistsReferrer_EntryTList", sq); }

    public Map<String, EventTCQ> xdfgetUserId_ExistsReferrer_EventTList() { return xgetSQueMap("userId_ExistsReferrer_EventTList"); }
    public String keepUserId_ExistsReferrer_EventTList(EventTCQ sq) { return xkeepSQue("userId_ExistsReferrer_EventTList", sq); }

    public Map<String, EntryTCQ> xdfgetUserId_NotExistsReferrer_EntryTList() { return xgetSQueMap("userId_NotExistsReferrer_EntryTList"); }
    public String keepUserId_NotExistsReferrer_EntryTList(EntryTCQ sq) { return xkeepSQue("userId_NotExistsReferrer_EntryTList", sq); }

    public Map<String, EventTCQ> xdfgetUserId_NotExistsReferrer_EventTList() { return xgetSQueMap("userId_NotExistsReferrer_EventTList"); }
    public String keepUserId_NotExistsReferrer_EventTList(EventTCQ sq) { return xkeepSQue("userId_NotExistsReferrer_EventTList", sq); }

    public Map<String, EntryTCQ> xdfgetUserId_SpecifyDerivedReferrer_EntryTList() { return xgetSQueMap("userId_SpecifyDerivedReferrer_EntryTList"); }
    public String keepUserId_SpecifyDerivedReferrer_EntryTList(EntryTCQ sq) { return xkeepSQue("userId_SpecifyDerivedReferrer_EntryTList", sq); }

    public Map<String, EventTCQ> xdfgetUserId_SpecifyDerivedReferrer_EventTList() { return xgetSQueMap("userId_SpecifyDerivedReferrer_EventTList"); }
    public String keepUserId_SpecifyDerivedReferrer_EventTList(EventTCQ sq) { return xkeepSQue("userId_SpecifyDerivedReferrer_EventTList", sq); }

    public Map<String, EntryTCQ> xdfgetUserId_QueryDerivedReferrer_EntryTList() { return xgetSQueMap("userId_QueryDerivedReferrer_EntryTList"); }
    public String keepUserId_QueryDerivedReferrer_EntryTList(EntryTCQ sq) { return xkeepSQue("userId_QueryDerivedReferrer_EntryTList", sq); }
    public Map<String, Object> xdfgetUserId_QueryDerivedReferrer_EntryTListParameter() { return xgetSQuePmMap("userId_QueryDerivedReferrer_EntryTList"); }
    public String keepUserId_QueryDerivedReferrer_EntryTListParameter(Object pm) { return xkeepSQuePm("userId_QueryDerivedReferrer_EntryTList", pm); }

    public Map<String, EventTCQ> xdfgetUserId_QueryDerivedReferrer_EventTList() { return xgetSQueMap("userId_QueryDerivedReferrer_EventTList"); }
    public String keepUserId_QueryDerivedReferrer_EventTList(EventTCQ sq) { return xkeepSQue("userId_QueryDerivedReferrer_EventTList", sq); }
    public Map<String, Object> xdfgetUserId_QueryDerivedReferrer_EventTListParameter() { return xgetSQuePmMap("userId_QueryDerivedReferrer_EventTList"); }
    public String keepUserId_QueryDerivedReferrer_EventTListParameter(Object pm) { return xkeepSQuePm("userId_QueryDerivedReferrer_EventTList", pm); }

    /** 
     * Add order-by as ascend. <br>
     * user_id: {PK, ID, NotNull, serial(10)}
     * @return this. (NotNull)
     */
    public BsUserTCQ addOrderBy_UserId_Asc() { regOBA("user_id"); return this; }

    /**
     * Add order-by as descend. <br>
     * user_id: {PK, ID, NotNull, serial(10)}
     * @return this. (NotNull)
     */
    public BsUserTCQ addOrderBy_UserId_Desc() { regOBD("user_id"); return this; }

    protected ConditionValue _userName;
    public ConditionValue xdfgetUserName()
    { if (_userName == null) { _userName = nCV(); }
      return _userName; }
    protected ConditionValue xgetCValueUserName() { return xdfgetUserName(); }

    /** 
     * Add order-by as ascend. <br>
     * user_name: {NotNull, text(2147483647)}
     * @return this. (NotNull)
     */
    public BsUserTCQ addOrderBy_UserName_Asc() { regOBA("user_name"); return this; }

    /**
     * Add order-by as descend. <br>
     * user_name: {NotNull, text(2147483647)}
     * @return this. (NotNull)
     */
    public BsUserTCQ addOrderBy_UserName_Desc() { regOBD("user_name"); return this; }

    protected ConditionValue _email;
    public ConditionValue xdfgetEmail()
    { if (_email == null) { _email = nCV(); }
      return _email; }
    protected ConditionValue xgetCValueEmail() { return xdfgetEmail(); }

    /** 
     * Add order-by as ascend. <br>
     * email: {text(2147483647)}
     * @return this. (NotNull)
     */
    public BsUserTCQ addOrderBy_Email_Asc() { regOBA("email"); return this; }

    /**
     * Add order-by as descend. <br>
     * email: {text(2147483647)}
     * @return this. (NotNull)
     */
    public BsUserTCQ addOrderBy_Email_Desc() { regOBD("email"); return this; }

    protected ConditionValue _userType;
    public ConditionValue xdfgetUserType()
    { if (_userType == null) { _userType = nCV(); }
      return _userType; }
    protected ConditionValue xgetCValueUserType() { return xdfgetUserType(); }

    /** 
     * Add order-by as ascend. <br>
     * user_type: {int4(10)}
     * @return this. (NotNull)
     */
    public BsUserTCQ addOrderBy_UserType_Asc() { regOBA("user_type"); return this; }

    /**
     * Add order-by as descend. <br>
     * user_type: {int4(10)}
     * @return this. (NotNull)
     */
    public BsUserTCQ addOrderBy_UserType_Desc() { regOBD("user_type"); return this; }

    protected ConditionValue _password;
    public ConditionValue xdfgetPassword()
    { if (_password == null) { _password = nCV(); }
      return _password; }
    protected ConditionValue xgetCValuePassword() { return xdfgetPassword(); }

    /** 
     * Add order-by as ascend. <br>
     * password: {NotNull, text(2147483647)}
     * @return this. (NotNull)
     */
    public BsUserTCQ addOrderBy_Password_Asc() { regOBA("password"); return this; }

    /**
     * Add order-by as descend. <br>
     * password: {NotNull, text(2147483647)}
     * @return this. (NotNull)
     */
    public BsUserTCQ addOrderBy_Password_Desc() { regOBD("password"); return this; }

    protected ConditionValue _authLevel;
    public ConditionValue xdfgetAuthLevel()
    { if (_authLevel == null) { _authLevel = nCV(); }
      return _authLevel; }
    protected ConditionValue xgetCValueAuthLevel() { return xdfgetAuthLevel(); }

    /** 
     * Add order-by as ascend. <br>
     * auth_level: {NotNull, int4(10), default=[0]}
     * @return this. (NotNull)
     */
    public BsUserTCQ addOrderBy_AuthLevel_Asc() { regOBA("auth_level"); return this; }

    /**
     * Add order-by as descend. <br>
     * auth_level: {NotNull, int4(10), default=[0]}
     * @return this. (NotNull)
     */
    public BsUserTCQ addOrderBy_AuthLevel_Desc() { regOBD("auth_level"); return this; }

    protected ConditionValue _moneyId;
    public ConditionValue xdfgetMoneyId()
    { if (_moneyId == null) { _moneyId = nCV(); }
      return _moneyId; }
    protected ConditionValue xgetCValueMoneyId() { return xdfgetMoneyId(); }

    /** 
     * Add order-by as ascend. <br>
     * money_id: {NotNull, int4(10), FK to subsidy_mng_m}
     * @return this. (NotNull)
     */
    public BsUserTCQ addOrderBy_MoneyId_Asc() { regOBA("money_id"); return this; }

    /**
     * Add order-by as descend. <br>
     * money_id: {NotNull, int4(10), FK to subsidy_mng_m}
     * @return this. (NotNull)
     */
    public BsUserTCQ addOrderBy_MoneyId_Desc() { regOBD("money_id"); return this; }

    // ===================================================================================
    //                                                             SpecifiedDerivedOrderBy
    //                                                             =======================
    /**
     * Add order-by for specified derived column as ascend.
     * <pre>
     * cb.specify().derivedPurchaseList().max(new SubQuery&lt;PurchaseCB&gt;() {
     *     public void query(PurchaseCB subCB) {
     *         subCB.specify().columnPurchaseDatetime();
     *     }
     * }, <span style="color: #CC4747">aliasName</span>);
     * <span style="color: #3F7E5E">// order by [alias-name] asc</span>
     * cb.<span style="color: #CC4747">addSpecifiedDerivedOrderBy_Asc</span>(<span style="color: #CC4747">aliasName</span>);
     * </pre>
     * @param aliasName The alias name specified at (Specify)DerivedReferrer. (NotNull)
     * @return this. (NotNull)
     */
    public BsUserTCQ addSpecifiedDerivedOrderBy_Asc(String aliasName) { registerSpecifiedDerivedOrderBy_Asc(aliasName); return this; }

    /**
     * Add order-by for specified derived column as descend.
     * <pre>
     * cb.specify().derivedPurchaseList().max(new SubQuery&lt;PurchaseCB&gt;() {
     *     public void query(PurchaseCB subCB) {
     *         subCB.specify().columnPurchaseDatetime();
     *     }
     * }, <span style="color: #CC4747">aliasName</span>);
     * <span style="color: #3F7E5E">// order by [alias-name] desc</span>
     * cb.<span style="color: #CC4747">addSpecifiedDerivedOrderBy_Desc</span>(<span style="color: #CC4747">aliasName</span>);
     * </pre>
     * @param aliasName The alias name specified at (Specify)DerivedReferrer. (NotNull)
     * @return this. (NotNull)
     */
    public BsUserTCQ addSpecifiedDerivedOrderBy_Desc(String aliasName) { registerSpecifiedDerivedOrderBy_Desc(aliasName); return this; }

    // ===================================================================================
    //                                                                         Union Query
    //                                                                         ===========
    public void reflectRelationOnUnionQuery(ConditionQuery bqs, ConditionQuery uqs) {
        UserTCQ bq = (UserTCQ)bqs;
        UserTCQ uq = (UserTCQ)uqs;
        if (bq.hasConditionQuerySubsidyMngM()) {
            uq.querySubsidyMngM().reflectRelationOnUnionQuery(bq.querySubsidyMngM(), uq.querySubsidyMngM());
        }
    }

    // ===================================================================================
    //                                                                       Foreign Query
    //                                                                       =============
    /**
     * Get the condition-query for relation table. <br>
     * subsidy_mng_m by my money_id, named 'subsidyMngM'.
     * @return The instance of condition-query. (NotNull)
     */
    public SubsidyMngMCQ querySubsidyMngM() {
        return xdfgetConditionQuerySubsidyMngM();
    }
    public SubsidyMngMCQ xdfgetConditionQuerySubsidyMngM() {
        String prop = "subsidyMngM";
        if (!xhasQueRlMap(prop)) { xregQueRl(prop, xcreateQuerySubsidyMngM()); xsetupOuterJoinSubsidyMngM(); }
        return xgetQueRlMap(prop);
    }
    protected SubsidyMngMCQ xcreateQuerySubsidyMngM() {
        String nrp = xresolveNRP("user_t", "subsidyMngM"); String jan = xresolveJAN(nrp, xgetNNLvl());
        return xinitRelCQ(new SubsidyMngMCQ(this, xgetSqlClause(), jan, xgetNNLvl()), _baseCB, "subsidyMngM", nrp);
    }
    protected void xsetupOuterJoinSubsidyMngM() { xregOutJo("subsidyMngM"); }
    public boolean hasConditionQuerySubsidyMngM() { return xhasQueRlMap("subsidyMngM"); }

    protected Map<String, Object> xfindFixedConditionDynamicParameterMap(String property) {
        return null;
    }

    // ===================================================================================
    //                                                                     ScalarCondition
    //                                                                     ===============
    public Map<String, UserTCQ> xdfgetScalarCondition() { return xgetSQueMap("scalarCondition"); }
    public String keepScalarCondition(UserTCQ sq) { return xkeepSQue("scalarCondition", sq); }

    // ===================================================================================
    //                                                                       MyselfDerived
    //                                                                       =============
    public Map<String, UserTCQ> xdfgetSpecifyMyselfDerived() { return xgetSQueMap("specifyMyselfDerived"); }
    public String keepSpecifyMyselfDerived(UserTCQ sq) { return xkeepSQue("specifyMyselfDerived", sq); }

    public Map<String, UserTCQ> xdfgetQueryMyselfDerived() { return xgetSQueMap("queryMyselfDerived"); }
    public String keepQueryMyselfDerived(UserTCQ sq) { return xkeepSQue("queryMyselfDerived", sq); }
    public Map<String, Object> xdfgetQueryMyselfDerivedParameter() { return xgetSQuePmMap("queryMyselfDerived"); }
    public String keepQueryMyselfDerivedParameter(Object pm) { return xkeepSQuePm("queryMyselfDerived", pm); }

    // ===================================================================================
    //                                                                        MyselfExists
    //                                                                        ============
    protected Map<String, UserTCQ> _myselfExistsMap;
    public Map<String, UserTCQ> xdfgetMyselfExists() { return xgetSQueMap("myselfExists"); }
    public String keepMyselfExists(UserTCQ sq) { return xkeepSQue("myselfExists", sq); }

    // ===================================================================================
    //                                                                       MyselfInScope
    //                                                                       =============
    public Map<String, UserTCQ> xdfgetMyselfInScope() { return xgetSQueMap("myselfInScope"); }
    public String keepMyselfInScope(UserTCQ sq) { return xkeepSQue("myselfInScope", sq); }

    // ===================================================================================
    //                                                                       Very Internal
    //                                                                       =============
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xCB() { return UserTCB.class.getName(); }
    protected String xCQ() { return UserTCQ.class.getName(); }
    protected String xCHp() { return HpQDRFunction.class.getName(); }
    protected String xCOp() { return ConditionOption.class.getName(); }
    protected String xMap() { return Map.class.getName(); }
}
