/*USUARIO POSTGRES: postgres
  PASSWORD: root
  NOMBRE BASE DE DATOS: bd_inno
*/
/*==============================================================*/
/* User: PUBLIC                                                 */
/*==============================================================*/
/*==============================================================*/
/* Table: DRON_PERSONA                                          */
/*==============================================================*/
CREATE TABLE reg_dron_persona
(
  per_cedula character varying(10) NOT NULL,
  per_apellidos character varying(150),
  per_correo character varying(100),
  per_experiencia boolean,
  per_fecha date,
  per_fecha_reg timestamp without time zone,
  per_nacionalidad character varying(100),
  per_nombres character varying(150),
  per_periodo character varying(6),
  per_poseedron boolean,
  per_residencia character varying(200),
  per_telefono character varying(50),
  per_interes character varying(200),
  CONSTRAINT reg_dron_persona_pkey PRIMARY KEY (per_cedula)
);
