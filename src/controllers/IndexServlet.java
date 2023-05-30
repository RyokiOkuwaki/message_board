//データベースから複数のメッセージ情報を取得して一覧表示するサーブレット

package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import utils.DBUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();

        //Messageクラスで名前を付けたSELECT文 getAllMessages を createNamedQuery メソッドの引数に指定し、データベースへの問い合わせを行う
        //問い合わせ結果を　getResultList() メソッドを使ってリスト形式で取得する。
        //データベースに保存されたデータはHibernateによって自動でMessageクラスのオブジェクトになりリストの中に格納される。
        List<Message> messages = em.createNamedQuery("getAllMessages", Message.class).getResultList();

        em.close();

        //データベースから取得したメッセージ一覧(message)をリクエストスコープにセットし、 index.jspを呼び出す(入力データを送る)
        request.setAttribute("messages", messages);

        // フラッシュメッセージがセッションスコープにセットされていたら
        // リクエストスコープに保存する（セッションスコープからは削除）
        if (request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        //ビューとなるJSPを指定して表示する
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/index.jsp");
        rd.forward(request, response);
    }

}
