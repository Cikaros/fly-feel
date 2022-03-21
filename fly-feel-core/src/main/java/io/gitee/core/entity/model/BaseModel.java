package io.gitee.core.entity.model;

import io.gitee.core.entity.query.verify.UpdateGroup;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStringValue;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 默认Model类
 *
 * @author Cikaros
 * @date 2021/9/10
 */
public abstract class BaseModel implements Cloneable {

    /**
     * 主键
     */
    @NotNull(groups = UpdateGroup.class, message = "主键id不能为null！")
    @Min(groups = {UpdateGroup.class}, message = "主键id不能小于1！", value = 1)
    @PodamExclude
    private Long id;

    /**
     * 是否为系统数据
     */
    private Boolean system;

    /**
     * 可用状态
     */
    private Boolean enable;

    /**
     * 删除标记
     */
    private Boolean del;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建时间
     */
    @PodamExclude
    private Date createTime;

    /**
     * 创建时间范围
     */
    @PodamExclude
    private Date createBefore;

    /**
     * 创建时间范围
     */
    @PodamExclude
    private Date createAfter;

    /**
     * 修改人
     */
    private Long updateBy;

    /**
     * 修改时间
     */
    @PodamExclude
    private Date updateTime;

    /**
     * 修改时间范围
     */
    @PodamExclude
    private Date updateBefore;

    /**
     * 修改时间范围
     */
    @PodamExclude
    private Date updateAfter;

    /**
     * 备注
     */
    @PodamStringValue(strValue = "该数据由fly-feel测试生成！")
    private String remark;

    /**
     * 记录版本号
     */
    @PodamExclude
    private Long _version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getSystem() {
        return system;
    }

    public void setSystem(Boolean system) {
        this.system = system;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateBefore() {
        return createBefore;
    }

    public void setCreateBefore(Date createBefore) {
        this.createBefore = createBefore;
    }

    public Date getCreateAfter() {
        return createAfter;
    }

    public void setCreateAfter(Date createAfter) {
        this.createAfter = createAfter;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateBefore() {
        return updateBefore;
    }

    public void setUpdateBefore(Date updateBefore) {
        this.updateBefore = updateBefore;
    }

    public Date getUpdateAfter() {
        return updateAfter;
    }

    public void setUpdateAfter(Date updateAfter) {
        this.updateAfter = updateAfter;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long get_version() {
        return _version;
    }

    public void set_version(Long _version) {
        this._version = _version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseModel baseModel = (BaseModel) o;
        return Objects.equals(id, baseModel.id) && Objects.equals(system, baseModel.system) && Objects.equals(enable, baseModel.enable) && Objects.equals(del, baseModel.del) && Objects.equals(createBy, baseModel.createBy) && Objects.equals(createTime, baseModel.createTime) && Objects.equals(createBefore, baseModel.createBefore) && Objects.equals(createAfter, baseModel.createAfter) && Objects.equals(updateBy, baseModel.updateBy) && Objects.equals(updateTime, baseModel.updateTime) && Objects.equals(updateBefore, baseModel.updateBefore) && Objects.equals(updateAfter, baseModel.updateAfter) && Objects.equals(remark, baseModel.remark) && Objects.equals(_version, baseModel._version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, system, enable, del, createBy, createTime, createBefore, createAfter, updateBy, updateTime, updateBefore, updateAfter, remark, _version);
    }

    public Object clone(BaseModel.Field... fields) {
        Map<String, Object> clone = new HashMap<>(6);
        if (fields.length > 0) {
            for (Field field : fields) {
                switch (field) {
                    case ID:
                        clone.put("id", this.getId());
                        break;
                    case SYSTEM:
                        clone.put("system", this.getSystem());
                        break;
                    case ENABLE:
                        clone.put("enable", this.getEnable());
                        break;
                    case DEL:
                        clone.put("del", this.getDel());
                        break;
                    case CREATE:
                        clone.put("createBy", this.getCreateBy());
                        clone.put("createTime", this.getCreateTime());
                        break;
                    case UPDATE:
                        clone.put("updateBy", this.getUpdateBy());
                        clone.put("updateTime", this.getUpdateTime());
                        break;
                    case REMARK:
                        clone.put("remark", this.getRemark());
                    default:
                }
            }
            clone.put("_version", this.get_version());
        }
        return clone;
    }

    @Override
    public BaseModel clone() {
        try {
            return (BaseModel) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public enum Field {
        ID,
        SYSTEM,
        ENABLE,
        DEL,
        CREATE,
        UPDATE,
        REMARK,
        _VERSION
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "id=" + id +
                ", system=" + system +
                ", enable=" + enable +
                ", del=" + del +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", createBefore=" + createBefore +
                ", createAfter=" + createAfter +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                ", updateBefore=" + updateBefore +
                ", updateAfter=" + updateAfter +
                ", remark='" + remark + '\'' +
                ", _version=" + _version +
                '}';
    }
}
