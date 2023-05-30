package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;//@NamedQueriesのためのインポート
import javax.persistence.NamedQuery;//
import javax.persistence.Table;

@Entity

//Messageクラスに一覧表示するための'JPQL(SQL文(SELECT文))'を追記
@NamedQueries({
        //'query'が示す内容のSELECT文に'getAllMessage'という名前をつける
        //SELECT文の意味：全てのデータをMessageクラスからID番号の降順で取得する
        @NamedQuery(name = "getAllMessages", query = "SELECT m FROM Message AS m ORDER BY m.id DESC"),
        @NamedQuery(name = "getMessagesCount", query = "SELECT COUNT(m) FROM Message AS m")
})

@Table(name = "messages")
public class Message {
    @Id
    @Column(name = "id") //メッセージID、数値型、自動採番
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主キー値を自動採番する
    private Integer id;

    @Column(name = "title", length = 255, nullable = false) //メッセージのタイトル
    private String title;

    @Column(name = "content", length = 255, nullable = false) //メッセージの内容
    private String content;

    @Column(name = "created_at", nullable = false) //作成日時
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false) //更新日時
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
