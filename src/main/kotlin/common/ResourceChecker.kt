package common

class ResourceChecker {
    companion object {
        private const val BYTES_TO_MB_DIVISOR = (1024 * 1024).toDouble()

        /**
         * Forces the GC to remove unused memory if [gc] then returns allocated memory in MB
         */
        fun memoryUsage(gc: Boolean = true): Double {
            val runtime = Runtime.getRuntime()
            if (gc) {
                runtime.gc()
            }
            return runtime.totalMemory().toDouble() / BYTES_TO_MB_DIVISOR
        }
    }
}
