package co.omisego.omisego.model.params

/*
 * OmiseGO
 *
 * Created by Phuchit Sirimongkolsathien on 20/3/2018 AD.
 * Copyright © 2017-2018 OmiseGO. All rights reserved.
 */

import co.omisego.omisego.model.filterable.Filter
import co.omisego.omisego.model.filterable.FilterableParams
import co.omisego.omisego.model.pagination.Paginable
import co.omisego.omisego.model.pagination.PaginableParams
import co.omisego.omisego.model.pagination.SortDirection

/**
 *  Represent a structure used to query a list of transactions
 */

data class AccountListParams internal constructor(
    /**
     * A page number
     */
    override val page: Int = 1,

    /**
     * A number of results per page.
     */
    override val perPage: Int = 10,

    /**
     * The sorting field
     *
     * The available values are
     * - [Paginable.Account.SortableFields.ID]
     * - [Paginable.Account.SortableFields.NAME]
     * - [Paginable.Account.SortableFields.DESCRIPTION]
     * - [Paginable.Account.SortableFields.CREATED_AT]
     * - [Paginable.Account.SortableFields.UPDATED_AT]
     */
    override val sortBy: Paginable.Account.SortableFields = Paginable.Account.SortableFields.CREATED_AT,

    /**
     * The desired sort direction
     *
     * The available values are
     * - [SortDirection.ASCENDING]
     * - [SortDirection.DESCENDING]
     */
    override val sortDir: SortDirection = SortDirection.DESCENDING,

    /**
     * All provided conditions are matched for a record to be returned
     */
    override val matchAll: List<Filter>? = null,

    /**
     * All provided conditions are matched for a record to be returned
     */
    override val matchAny: List<Filter>? = null
) : PaginableParams, FilterableParams {
    companion object {
        fun create(
            page: Int = 1,
            perPage: Int = 10,
            sortBy: Paginable.Account.SortableFields = Paginable.Account.SortableFields.CREATED_AT,
            sortDir: SortDirection = SortDirection.DESCENDING,
            matchAll: List<Filter>? = null,
            matchAny: List<Filter>? = null
        ) = AccountListParams(
            page = page,
            perPage = perPage,
            sortBy = sortBy,
            sortDir = sortDir,
            matchAll = matchAll,
            matchAny = matchAny
        )
    }
}
