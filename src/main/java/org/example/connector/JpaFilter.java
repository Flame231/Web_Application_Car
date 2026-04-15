package org.example.connector;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Аннотация вешает фильтр на все запросы в приложении
@WebFilter("/*")
public class JpaFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Вызывается один раз при старте сервера
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Если тебе нужно работать именно с HTTP методами внутри фильтра:
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Для отладки можно вывести URL (по желанию):
        // System.out.println("Запрос к: " + httpRequest.getRequestURI());

        try {
            // Пропускаем запрос дальше (в твой Сервлет)
            chain.doFilter(request, response);

        } finally {
            // ЭТО САМОЕ ВАЖНОЕ:
            // Когда сервлет закончил работу (или упал с ошибкой),
            // мы закрываем EntityManager и очищаем ThreadLocal.
            HibernateUtil.closeEntityManager();
        }
    }

    @Override
    public void destroy() {
        // Вызывается при остановке сервера
    }
}
