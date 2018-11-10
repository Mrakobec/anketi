INSERT INTO public.statusmanagement_status_model
(id, uuid, delete_ts, deleted_by, name, entity_name)
VALUES('anketiStat', '795136b8-3752-4045-8dce-52c9e44bcbd2', NULL, NULL, 'Анкетирование', 'anketi$Questionnaire')^

INSERT INTO public.statusmanagement_status
(id, uuid, delete_ts, deleted_by, "name", sort_number, is_initial, status_model_id)
VALUES('anketiStat_new', '9e32f0fe-2e1f-46f1-b1ec-62d04bde86d6', NULL, NULL, 'Новая', NULL, true, 'anketiStat');
INSERT INTO public.statusmanagement_status
(id, uuid, delete_ts, deleted_by, "name", sort_number, is_initial, status_model_id)
VALUES('anketiStat_published', 'b67120aa-a74f-426a-b422-465f26448b03', NULL, NULL, 'Опубликована', NULL, NULL, 'anketiStat');
INSERT INTO public.statusmanagement_status
(id, uuid, delete_ts, deleted_by, "name", sort_number, is_initial, status_model_id)
VALUES('anketiStat_inTheArchive', '39cd1587-e8ce-4b37-85d8-8e309900d550', NULL, NULL, 'В архиве', NULL, NULL, 'anketiStat');
INSERT INTO public.statusmanagement_status
(id, uuid, delete_ts, deleted_by, "name", sort_number, is_initial, status_model_id)
VALUES('anketiStat_onCompletion', 'cbb69a49-7774-404f-9814-91931cbba7f5', NULL, NULL, 'На доработке', NULL, NULL, 'anketiStat')^

INSERT INTO public.statusmanagement_status_transition
(id, delete_ts, deleted_by, action_name, status1_id, status2_id, status_model_id, comment_required)
VALUES('fbf13df5-3b86-4c48-a9e0-0c9d296d9617', NULL, NULL, 'Опубликовать', 'anketiStat_new', 'anketiStat_published', 'anketiStat', false);
INSERT INTO public.statusmanagement_status_transition
(id, delete_ts, deleted_by, action_name, status1_id, status2_id, status_model_id, comment_required)
VALUES('94f13e72-6f0b-4ec2-8ef4-1bd2dca4ff5f', NULL, NULL, 'Отправить на доработку', 'anketiStat_new', 'anketiStat_onCompletion', 'anketiStat', false);
INSERT INTO public.statusmanagement_status_transition
(id, delete_ts, deleted_by, action_name, status1_id, status2_id, status_model_id, comment_required)
VALUES('23caf8d1-0803-4154-a37c-cbf237ab9f4a', NULL, NULL, 'Опубликовать', 'anketiStat_onCompletion', 'anketiStat_published', 'anketiStat', false);
INSERT INTO public.statusmanagement_status_transition
(id, delete_ts, deleted_by, action_name, status1_id, status2_id, status_model_id, comment_required)
VALUES('384fb05a-7bf8-4307-b46f-8193dc62e1b4', NULL, NULL, 'Отправить в архив', 'anketiStat_published', 'anketiStat_inTheArchive', 'anketiStat', false)^
