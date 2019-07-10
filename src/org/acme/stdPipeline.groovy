#!/bin/groovy
package org.acme;

def execute() {

  node {

    stage('Initialize') {
      checkout scm
      echo 'Loading pipeline definition'
      //Yaml parser = new Yaml()
      //Map pipelineDefinition = parser.load(new File(pwd() + '/var/lib/jenkins/workspace/test-sl/pipeline.yaml').text)
      Map pipelineCfg = readYaml(file: "${WORKSPACE}/pipeline.yaml")
    }

    switch(pipelineCfg.pipelineType) {
      case 'maven':
        // Instantiate and execute a Python pipeline
         new mavenPipeline(pipelineCfg).executePipeline()
      case 'nodejs':
        // Instantiate and execute a NodeJS pipeline
         new nodeJSPipeline(pipelineCfg).executePipeline()
    }

  }

}
