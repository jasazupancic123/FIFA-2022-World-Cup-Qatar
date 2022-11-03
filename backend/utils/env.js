module.exports = class EnvUtil {
    static getDatabaseUrl() {
        switch (process.env.NODE_ENV) {
            case 'development':
                return process.env.DB_URL_DEV
            case 'production':
                return process.env.DB_URL_PROD
            default:
                return process.env.DB_URL_DEV
    }
} 