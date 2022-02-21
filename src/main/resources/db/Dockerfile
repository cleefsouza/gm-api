# db local instance for integration tests

FROM postgres:10.6-alpine

ENV POSTGRES_DB=ifood-benf-corp-manager
ENV POSTGRES_USERNAME=db_user
ENV POSTGRES_PASSWORD=db_pass

COPY init_db.sql /docker-entrypoint-initdb.d/

EXPOSE 5432