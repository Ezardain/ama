# Model Activity
# Table activities
class Activity < ActiveRecord::Base
  has_many :attachments

  validates :name, presence: true
  validates :description, presence: true
  validates :hours, presence: true
end
