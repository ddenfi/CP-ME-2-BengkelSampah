package com.bengkelsampah.bengkelsampahapp.data.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.bengkelsampah.bengkelsampahapp.domain.model.UserPreferencesDataModel
import com.bengkelsampah.bengkelsampahapp.domain.model.UserRole
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceDataStore @Inject constructor(
    private val userPreferences: DataStore<Preferences>
) {

    val userPreferencesData: Flow<UserPreferencesDataModel> = userPreferences.data
        .map { preferences ->
            val isLogin = preferences[IS_LOGIN] ?: false
            val shouldShowOnboard = preferences[SHOW_ONBOARD] ?: true
            val userRole = preferences[USER_ROLE] ?: UserRole.CONSUMER.name

            UserPreferencesDataModel(
                isLogin,
                shouldShowOnboard,
                userRole = UserRole.valueOf(userRole)
            )
        }

    suspend fun setLoginStatus(isLogin: Boolean) {
        userPreferences.edit { preferences ->
            preferences[IS_LOGIN] = isLogin
        }
    }

    suspend fun setShouldShowOnboard(shouldShowOnboard: Boolean) {
        userPreferences.edit { preferences ->
            preferences[SHOW_ONBOARD] = shouldShowOnboard
        }
    }

    suspend fun setUserRole(userRole: UserRole) {
        userPreferences.edit { preferences ->
            preferences[USER_ROLE] = userRole.name
        }
    }


    private companion object PreferencesKeys {
        val IS_LOGIN = booleanPreferencesKey("is_login")
        val SHOW_ONBOARD = booleanPreferencesKey("show_onboard")
        val USER_ROLE = stringPreferencesKey("user_role")
    }

}