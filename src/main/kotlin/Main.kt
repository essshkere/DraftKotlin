fun main() {

}

class post(
    val id: Int, //идентификатор записи
    val owner_id: Int, // ид владельца стены
    val from_id: Int // Идентификатор автора записи (от чьего имени опубликована запись)
    val date: Int, //   Время публикации записи в формате unixtime.
    val text: String,//    Текст записи.
    val comments: comments = comments(0, true, true, true, true) //Информация о комментариях к записи, объект с полями
    val post_type: String, //    Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest
    val can_pin: Boolean//    Информация о том, может ли текущий пользователь закрепить запись (1 — может, 0 — не может).
    val can_delete: Boolean, //Информация о том, может ли текущий пользователь удалить запись (1 — может, 0 — не может).
    val can_edit: Boolean //Информация о том, может ли текущий пользователь редактировать запись (1 — может, 0 — не может).
)

class comments(
    val count: Int, // — количество комментариев;
    val can_post: Boolean, //— информация о том, может ли текущий пользователь комментировать запись (1 — может, 0 — не может);
    val groups_can_post: Boolean, //— информация о том, могут ли сообщества комментировать запись;
    val can_close: Boolean, //— может ли текущий пользователь закрыть комментарии к записи;
    val can_open: Boolean //— может ли текущий пользователь открыть комментарии к записи.
)