package org.example.connector;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("academy");

    // 1. Создаем хранилище, привязанное к потокам
    private static final ThreadLocal<EntityManager> threadLocalEm = new ThreadLocal<>();

    public static EntityManager getEntityManager() {
        // 2. Проверяем, есть ли уже EM в текущем потоке
        EntityManager em = threadLocalEm.get();

        // 3. Если нет (или он закрыт) — создаем и сохраняем
        if (em == null || !em.isOpen()) {
            em = ENTITY_MANAGER_FACTORY.createEntityManager();
            threadLocalEm.set(em);
        }
        return em;
    }

    // 4. Метод для очистки (вызывается в конце запроса в Фильтре)
    public static void closeEntityManager() {
        EntityManager em = threadLocalEm.get();
        if (em != null) {
            if (em.isOpen()) {
                em.close();
            }
            // 5. ОБЯЗАТЕЛЬНО удаляем связь, чтобы не было утечек памяти
            threadLocalEm.remove();
        }
    }

    public static void closeFactory() {
        if (ENTITY_MANAGER_FACTORY.isOpen()) {
            ENTITY_MANAGER_FACTORY.close();
        }
    }
}
