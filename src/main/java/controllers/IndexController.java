package controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Create 6/29/2021
 * @Extends of {@link HttpServlet} class.
 */


// TODO
public class IndexController extends HttpServlet {

    /**
     * ОСНОВНЫЕ ПОНЯТИЯ
     *
     *
     * {@link javax.servlet.Servlet} - это интерфейсом реализация которого расширяет функциональные возможности сервера.
     * Он взаимодействует с клиентами посредством принципа запрос-ответ.
     *
     * Класс сервлета наследуется от класса {@link HttpServlet}.
     *
     * Для обработки GET-запросов (например, при обращении к сервлету из адресной строки браузера) сервлет должен переопределить метод doGet.
     * То есть, к примеру, в данном случае get-запрос по адресу /hello будет обрабатываться методом doGet.
     *
     * Этот метод принимает два параметра. Параметр типа {@link HttpServletRequest} инкапсулирует всю информацию о запросе.
     * А параметр типа {@link HttpServletResponse} позволяет управлять ответом.
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}