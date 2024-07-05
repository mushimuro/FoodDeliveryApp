package com.sparta.fooddeliveryapp.global.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCreateTimeStamped is a Querydsl query type for CreateTimeStamped
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QCreateTimeStamped extends EntityPathBase<CreateTimeStamped> {

    private static final long serialVersionUID = -1744445956L;

    public static final QCreateTimeStamped createTimeStamped = new QCreateTimeStamped("createTimeStamped");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public QCreateTimeStamped(String variable) {
        super(CreateTimeStamped.class, forVariable(variable));
    }

    public QCreateTimeStamped(Path<? extends CreateTimeStamped> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCreateTimeStamped(PathMetadata metadata) {
        super(CreateTimeStamped.class, metadata);
    }

}

