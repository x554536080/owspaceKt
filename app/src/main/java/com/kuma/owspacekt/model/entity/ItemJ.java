package com.kuma.owspacekt.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ItemJ implements Parcelable {


    private String id;
    private String uid;
    private String name;
    private String title;
    private String excerpt;
    private String lead;
    private String model;
    private String position;
    private String thumbnail;
    private String create_time;
    private String updateTime;
    private String status;
    private String video;
    private String fm;
    private String linkUrl;
    private String publishTime;
    private String view;
    private String share;
    private String comment;
    private String good;
    private String bookmark;
    private String showUid;
    private String fmPlay;
    //    private String lunarType;
    private String html5;
    private String author;
    private Integer tpl;
    private String avatar;
    private String category;
    private Integer parseXML;
    private List<TagsDTO> tags;
    private List<HotCommentsDTO> hot_comments;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.uid);
        dest.writeString(this.name);
        dest.writeString(this.title);
        dest.writeString(this.excerpt);
        dest.writeString(this.lead);
        dest.writeString(this.model);
        dest.writeString(this.position);
        dest.writeString(this.thumbnail);
        dest.writeString(this.create_time);
        dest.writeString(this.updateTime);
        dest.writeString(this.status);
        dest.writeString(this.video);
        dest.writeString(this.fm);
        dest.writeString(this.linkUrl);
        dest.writeString(this.publishTime);
        dest.writeString(this.view);
        dest.writeString(this.share);
        dest.writeString(this.comment);
        dest.writeString(this.good);
        dest.writeString(this.bookmark);
        dest.writeString(this.showUid);
        dest.writeString(this.fmPlay);
//        dest.writeString(lunarType);
        dest.writeString(this.html5);
        dest.writeString(this.author);
        dest.writeInt(this.tpl);
        dest.writeString(this.avatar);
        dest.writeString(this.category);
        dest.writeInt(this.parseXML);
        dest.writeList(this.tags);
        dest.writeList(this.hot_comments);

    }

    public ItemJ() {
    }

    public ItemJ(Parcel in) {
        this.id = in.readString();
        this.uid = in.readString();
        this.name = in.readString();
        this.title = in.readString();
        this.excerpt = in.readString();
        this.lead = in.readString();
        this.model = in.readString();
        this.position = in.readString();
        this.thumbnail = in.readString();
        this.create_time = in.readString();
        this.updateTime = in.readString();
        this.status = in.readString();
        this.video = in.readString();
        this.fm = in.readString();
        this.linkUrl = in.readString();
        this.publishTime = in.readString();
        this.view = in.readString();
        this.share = in.readString();
        this.comment = in.readString();
        this.good = in.readString();
        this.bookmark = in.readString();
        this.showUid = in.readString();
        this.fmPlay = in.readString();
        this.html5 = in.readString();
        this.author = in.readString();
        this.tpl = in.readInt();
        this.avatar = in.readString();
        this.category = in.readString();
        this.parseXML = in.readInt();
        this.tags = new ArrayList<>();
        in.readList(this.tags, TagsDTO.class.getClassLoader());
        this.hot_comments = new ArrayList<>();
        in.readList(this.hot_comments, HotCommentsDTO.class.getClassLoader());
    }


    public static final Creator<ItemJ> CREATOR = new Creator<ItemJ>() {
        @Override
        public ItemJ createFromParcel(Parcel source) {
            return new ItemJ(source);
        }

        @Override
        public ItemJ[] newArray(int size) {
            return new ItemJ[size];
        }
    };


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCreateTime() {
        return create_time;
    }

    public void setCreateTime(String createTime) {
        this.create_time = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getFm() {
        return fm;
    }

    public void setFm(String fm) {
        this.fm = fm;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public String getBookmark() {
        return bookmark;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }

    public String getShowUid() {
        return showUid;
    }

    public void setShowUid(String showUid) {
        this.showUid = showUid;
    }

    public String getFmPlay() {
        return fmPlay;
    }

    public void setFmPlay(String fmPlay) {
        this.fmPlay = fmPlay;
    }

//    public String getLunarType() {
//        return lunarType;
//    }

//    public void setLunarType(String lunarType) {
//        t his.lunarType = lunarType;
//    }

    public String getHtml5() {
        return html5;
    }

    public void setHtml5(String html5) {
        this.html5 = html5;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getTpl() {
        return tpl;
    }

    public void setTpl(Integer tpl) {
        this.tpl = tpl;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getParseXML() {
        return parseXML;
    }

    public void setParseXML(Integer parseXML) {
        this.parseXML = parseXML;
    }

    public List<TagsDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagsDTO> tags) {
        this.tags = tags;
    }

    public List<HotCommentsDTO> getHot_comments() {
        return hot_comments;
    }

    public void setHot_comments(List<HotCommentsDTO> hot_comments) {
        this.hot_comments = hot_comments;
    }

    public static class TagsDTO implements Parcelable {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int describeContents() {
            return 0;
        }


        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
        }

        public TagsDTO() {
        }

        public TagsDTO(Parcel in) {
            this.name = in.readString();
        }


        public static final Creator<TagsDTO> CREATOR = new Creator<TagsDTO>() {
            @Override
            public TagsDTO createFromParcel(Parcel source) {
                return new TagsDTO(source);
            }

            @Override
            public TagsDTO[] newArray(int size) {
                return new TagsDTO[0];
            }
        };

    }

    public static class HotCommentsDTO implements Parcelable {
        //目前的接口好像啥都没有，就先写个空类吧


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }

        public HotCommentsDTO() {
        }

        public HotCommentsDTO(Parcel in) {
        }

        public static final Creator<HotCommentsDTO> CREATOR = new Creator<HotCommentsDTO>() {
            @Override
            public HotCommentsDTO createFromParcel(Parcel source) {
                return new HotCommentsDTO(source);
            }

            @Override
            public HotCommentsDTO[] newArray(int size) {
                return new HotCommentsDTO[size];
            }
        };
    }

}
