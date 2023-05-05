def call(lvVersion, lvBitness, accessToken) {
	def diffDir = "${WORKSPACE}\\DIFFDIR"
	def stepsDir = "${WORKSPACE}\\${BUILD_SYSTEM_REPO}\\steps"
	def operationsDir = "${WORKSPACE}\\${BUILD_SYSTEM_REPO}\\lv\\operations"
	def prNum = env.CHANGE_ID
	def repo = getComponentParts()['repo']

	echo 'Running LabVIEW diff build between origin/main and this commit'

	bat "python -u \"${stepsDir}\\labview_diff.py\" \"${operationsDir}\\\\\" \"${diffDir}\\\\\" ${lvVersion} ${lvBitness} --target=origin/main"
    
	bat "python -u \"${stepsDir}\\github_commenter.py\" --token=\"${accessToken}\" --pic-dir=\"${diffDir}\" --pull-req=\"8\" --info=\"${ORG_NAME}/${repo}/8\" --pic-repo=\"${ORG_NAME}/${PIC_REPO}\""
}
