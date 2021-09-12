package com.immortalalexsan.githubusers.presentation.converters

import com.immortalalexsan.core.converters.OneWayConverter
import com.immortalalexsan.core.resources.ResourceManager
import com.immortalalexsan.githubusers.R
import com.immortalalexsan.githubusers.domain.models.UserMetadataModel
import com.immortalalexsan.githubusers.presentation.models.UserMetadataModelItem

/**
 * Однонаправленный конвертер для [UserMetadataModel] и [UserMetadataModelItem].
 *
 * @property resourceManager Менеджер ресурсов.
 */
internal class UserMetadataModelItemConverter(private val resourceManager: ResourceManager) :
    OneWayConverter<UserMetadataModel, UserMetadataModelItem> {

    override fun convertTo(
        from: UserMetadataModel,
        applyBody: (UserMetadataModelItem.() -> UserMetadataModelItem)?,
    ): UserMetadataModelItem {
        val blogTemplate = resourceManager.getString(R.string.user_metadata_blog_template)
        val companyTemplate = resourceManager.getString(R.string.user_metadata_company_template)
        val locationTemplate = resourceManager.getString(R.string.user_metadata_location_template)
        val emailTemplate = resourceManager.getString(R.string.user_metadata_email_template)
        val loginTemplate = resourceManager.getString(R.string.user_metadata_login_template)
        val nameTemplate = resourceManager.getString(R.string.user_metadata_name_template)

        return with(from) {
            UserMetadataModelItem(
                avatarUrl = avatarUrl,
                blogFormatted = String.format(blogTemplate, blog.undefinedOrThis()),
                companyFormatted = String.format(companyTemplate, company.undefinedOrThis()),
                locationFormatted = String.format(locationTemplate, location.undefinedOrThis()),
                emailFormatted = String.format(emailTemplate, email.undefinedOrThis()),
                loginFormatted = String.format(loginTemplate, login.undefinedOrThis()),
                nameFormatted = String.format(nameTemplate, name.undefinedOrThis()),
            )
        }
    }

    private fun String?.undefinedOrThis(): String =
        this ?: resourceManager.getString(R.string.undefined)
}
